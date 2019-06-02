package com.wrlus.seciot.fw.controller;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
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

import com.wrlus.seciot.fw.model.FwInfo;
import com.wrlus.seciot.fw.service.FwServiceImpl;
import com.wrlus.seciot.history.model.FwHistoryDao;
import com.wrlus.seciot.history.model.HistoryDao;
import com.wrlus.seciot.history.service.HistoryServiceImpl;
import com.wrlus.seciot.library.model.ThirdLibraryDao;
import com.wrlus.seciot.library.model.ThirdLibrary;
import com.wrlus.seciot.library.model.ThirdLibraryRiskDao;
import com.wrlus.seciot.library.service.ThirdLibraryServiceImpl;
import com.wrlus.seciot.platform.model.PlatformRiskDao;
import com.wrlus.seciot.platform.model.PlatformRiskResult;
import com.wrlus.seciot.platform.service.PlatformRiskServiceImpl;
import com.wrlus.seciot.util.os.OSUtil;
import com.wrlus.seciot.util.exception.FileUploadException;
import com.wrlus.seciot.util.exception.ReasonEnum;
import com.wrlus.seciot.util.exception.RootException;;

@Controller
@RequestMapping("/fw")
public class FwController {
	private static Logger log = LogManager.getLogger();
	@Autowired
	private FwServiceImpl fwService;
	@Autowired
	private ThirdLibraryServiceImpl thirdLibraryService;
	@Autowired
	private PlatformRiskServiceImpl platformRiskService;
	@Autowired
	private HistoryServiceImpl historyService;
	
	@ResponseBody
	@RequestMapping("/analysis")
	public Map<String, Object> analysis(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		// Windows: file:/C:/******/SecIoT/WebContent/WEB-INF/classes/
		// *nix: file:/mnt/******/SecIoT/WEB-INF/classes/
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		if (OSUtil.isWindows()) {
			path = path.replace("file:/", "");
		} else {
			path = path.replace("file:", "");
		}
		path = path.replace("WEB-INF/classes/", "attach/uploads/firmware/"+UUID.randomUUID().toString()+"/");
		if (OSUtil.isWindows()) {
			path = OSUtil.escapeUnixSeparator(path);
		}
		try {
//			保存上传文件
			File fwFile = this.resolveUploadFile((MultipartHttpServletRequest) request, path);
//			分析固件信息（binwalk）
			FwInfo fwInfo = fwService.getFwInfo(fwFile);
			fwInfo.setSize(fwFile.length());
//			提取固件（binwalk -Me），获得固件根路径
			File rootDir = fwService.getFwRootDirectory(fwInfo);
			fwInfo.setRootDir(rootDir.getAbsolutePath());
//			获得所有已知的第三方库信息
			List<ThirdLibraryDao> libraries = thirdLibraryService.getThirdLibraryAll();
//			保存存在的第三方库对象
			List<ThirdLibrary> thirdLibraries = new ArrayList<>();
//			保存第三方库名称和每种库包含风险内容的映射
			Map<String, List<ThirdLibraryRiskDao>> thirdLibraryRisks = new HashMap<>();
//			遍历所有已知第三方库
			for (ThirdLibraryDao libraryDao : libraries) {
//				获得第三方库信息
				ThirdLibrary library = fwService.getFwThirdLibrary(fwInfo, libraryDao.getName());
//				如果第三方库存在
				if (library.isAvaliable()) {
					thirdLibraries.add(library);
//					获取这种第三方库所包含的风险
					List<ThirdLibraryRiskDao> libraryRisks = thirdLibraryService.getThirdLibraryRiskByLibInfo(library.getName(), library.getVersion());
					thirdLibraryRisks.put(library.getName(), libraryRisks);
				}
			}
//			获得所有Firmware类型的平台风险
			List<PlatformRiskDao> platformRisks = platformRiskService.getPlatformRiskByCategory("Firmware");
			List<PlatformRiskResult> platformRiskResults = fwService.checkFwPlatformRisks(fwInfo, platformRisks.toArray(new PlatformRiskDao[0]));
//			清除绝对路径信息，防止路径泄露
			fwInfo.setPath("");
			fwInfo.setRootDir(fwInfo.getRootDir().split(".extracted")[1]);
//			返回状态码
			data.put("status", 0);
//			返回状态说明字符串
			data.put("reason", ReasonEnum.SUCCESS.get());
//			返回固件信息
			data.put("fw_info", fwInfo);
//			返回第三方库信息
			data.put("fw_lib", thirdLibraries);
//			返回每种第三方库的所有风险
			data.put("fw_lib_risk", thirdLibraryRisks);
//			返回平台风险详情
			data.put("fw_platform_risk", platformRiskResults);
			FwHistoryDao fwHistory = new FwHistoryDao();
			fwHistory.setId(UUID.randomUUID().toString());
			fwHistory.setFwinfo(fwInfo);
			fwHistory.setFwlib(thirdLibraries);
			fwHistory.setFwlibrisk(thirdLibraryRisks);
			fwHistory.setFwplatformrisk(platformRiskResults);
			historyService.addFwHistory(fwHistory);
			HistoryDao history = new HistoryDao();
			history.setId(UUID.randomUUID().toString());
			history.setName("FirmwareStatic-"+fwFile.getName());
			history.setTarget(fwFile.getName());
			history.setType("firmware-static");
			history.setUser(getAuthenticatedUsername());
			history.setDate(new Date(new java.util.Date().getTime()));
			history.setDetailid(fwHistory.getId());
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
