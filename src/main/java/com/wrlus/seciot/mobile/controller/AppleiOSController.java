package com.wrlus.seciot.mobile.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wrlus.seciot.mobile.model.IpaInfo;
import com.wrlus.seciot.mobile.service.AppleiOSServiceImpl;
import com.wrlus.seciot.platform.model.PlatformRiskDao;
import com.wrlus.seciot.platform.model.PlatformRiskResult;
import com.wrlus.seciot.platform.service.PlatformRiskServiceImpl;
import com.wrlus.seciot.util.exception.FileUploadException;
import com.wrlus.seciot.util.exception.ReasonEnum;
import com.wrlus.seciot.util.exception.RootException;
import com.wrlus.seciot.util.os.OSUtil;

@Controller
@RequestMapping("/appleios")
public class AppleiOSController {
	private static Logger log = LogManager.getLogger();
	@Autowired
	private AppleiOSServiceImpl appleiOSService;
	@Autowired
	private PlatformRiskServiceImpl platformRiskService;
	
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
//			返回APK信息
			data.put("ipa_info", ipaInfo);
//			返回APK所需权限
			data.put("ipa_permission", permissions);
//			返回APK平台风险详情
			data.put("ipa_platform_risk", platformRiskResults);
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
			File targetFile = new File(path + multipartFile.getOriginalFilename());
			multipartFile.transferTo(targetFile);
			return targetFile;
		} catch (Exception e) {
			throw new FileUploadException(e);
		}
	}
	
	public void cleanUploadFile(String path) {
		new File(path).delete();
	}
}
