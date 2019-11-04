package com.wrlus.seciot.mobile.controller;

import java.io.File;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wrlus.seciot.history.model.AppleiOSHistoryDao;
import com.wrlus.seciot.history.model.HistoryDao;
import com.wrlus.seciot.history.service.HistoryServiceImpl;
import com.wrlus.seciot.mobile.model.IpaInfo;
import com.wrlus.seciot.mobile.service.AppleiOSServiceImpl;
import com.wrlus.seciot.platform.model.PlatformRiskDao;
import com.wrlus.seciot.platform.model.PlatformRiskResult;
import com.wrlus.seciot.platform.service.PlatformRiskServiceImpl;
import com.wrlus.seciot.util.exception.FileUploadException;
import com.wrlus.seciot.util.exception.ReasonEnum;
import com.wrlus.seciot.util.exception.RootException;
import com.wrlus.seciot.util.os.OSUtil;
import com.wrlus.seciot.waf.XSSProtect;

@Controller
@RequestMapping("/appleios")
public class AppleiOSController {
	private static Logger log = LogManager.getLogger();
	@Autowired
	private AppleiOSServiceImpl appleiOSService;
	@Autowired
	private PlatformRiskServiceImpl platformRiskService;
	@Autowired
	private HistoryServiceImpl historyService;
	
	@ResponseBody
	@RequestMapping("/analysis")
	public Map<String, Object> analysis(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data=new HashMap<String, Object>();
		// Windows: file:/C:/******/SecIoT/WebContent/WEB-INF/classes/
		// *nix: file:/mnt/******/SecIoT/WEB-INF/classes/
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		if (OSUtil.isWindows()) {
			path = path.replace("file:/", "");
		} else {
			path = path.replace("file:", "");
		}
		path = path.replace("WEB-INF/classes/", "attach/uploads/ipa/"+UUID.randomUUID().toString()+"/");
		if (OSUtil.isWindows()) {
			path = OSUtil.escapeUnixSeparator(path);
		}
		try {
//			保存上传文件
			File ipaFile = this.resolveUploadFile((MultipartHttpServletRequest) request, path);
			IpaInfo ipaInfo = appleiOSService.getIpaInfo(ipaFile);
			ipaInfo.setSize(ipaFile.length());
			String[] permissions = appleiOSService.getiOSPermissions(ipaInfo);
			List<PlatformRiskDao> platformRisks = platformRiskService.getPlatformRiskByCategory("iOS");
			List<PlatformRiskResult> platformRiskResults = appleiOSService.checkIpaPlatformRisks(ipaInfo, platformRisks.toArray(new PlatformRiskDao[0]));;
//			清除绝对路径信息，防止路径泄露
			ipaInfo.setSourceFile("");
			ipaInfo.setInfoPlistFile("");
			ipaInfo.setPath("");
//			返回状态码
			data.put("status", 0);
//			返回状态说明字符串
			data.put("reason", "OK");
//			返回IPA信息
			data.put("ipa_info", ipaInfo);
//			返回IPA所需权限
			data.put("ipa_permission", permissions);
//			返回IPA平台风险详情
			data.put("ipa_platform_risk", platformRiskResults);
			AppleiOSHistoryDao appleiOSHistory = new AppleiOSHistoryDao();
			appleiOSHistory.setId(UUID.randomUUID().toString());
			appleiOSHistory.setIpainfo(ipaInfo);
			appleiOSHistory.setIpapermission(permissions);
			appleiOSHistory.setIpaplatformrisk(platformRiskResults);
			historyService.addAppleiOSHistory(appleiOSHistory);
			HistoryDao history = new HistoryDao();
			history.setId(UUID.randomUUID().toString());
			history.setName("iOSStatic-"+ipaFile.getName());
			history.setTarget(ipaFile.getName());
			history.setType("ios-static");
			history.setUser(getAuthenticatedUsername());
			history.setDate(new Date(new java.util.Date().getTime()));
			history.setDetailid(appleiOSHistory.getId());
			historyService.addHistory(history);
		} catch (RootException e) {
			log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			data.put("status", -1);
			data.put("reason", e.getReason().get());
		} catch (Exception e) {
			log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			data.put("status", -1);
			data.put("reason", ReasonEnum.UNKNOWN.get());
		}
		this.cleanUploadFile(path);
		return data;
	}
	
	public File resolveUploadFile(MultipartHttpServletRequest multipartRequest, String path) throws FileUploadException {
		try {
			MultipartFile multipartFile = multipartRequest.getFile("file");
			new File(path).mkdirs();
			String originalFilename = multipartFile.getOriginalFilename();
			if (!originalFilename.endsWith(".ipa")) {
				throw new FileUploadException("File type mismatch.");
			}
			File targetFile = new File(path + XSSProtect.escapeString(originalFilename));
			multipartFile.transferTo(targetFile);
			return targetFile;
		} catch (Exception e) {
			throw new FileUploadException(e);
		}
	}
	
	public void cleanUploadFile(String path) {
		new File(path).delete();
	}
	
	public static String getAuthenticatedUsername() { 
		String username; 
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		if (principal instanceof UserDetails) { 
			username = ((UserDetails) principal).getUsername(); 
		} else { 
			username = principal.toString(); 
		} 
		return username; 
	}
}
