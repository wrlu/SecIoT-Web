package com.wrlus.seciot.mobile.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wrlus.seciot.device.service.DeviceServiceImpl;
import com.wrlus.seciot.history.model.AndroidHistoryDao;
import com.wrlus.seciot.history.model.HistoryDao;
import com.wrlus.seciot.history.service.HistoryServiceImpl;
import com.wrlus.seciot.mobile.model.ApkInfo;
import com.wrlus.seciot.mobile.model.HookResult;
import com.wrlus.seciot.mobile.model.MonitoringParameter;
import com.wrlus.seciot.mobile.service.AndroidServiceImpl;
import com.wrlus.seciot.platform.model.PlatformRiskDao;
import com.wrlus.seciot.platform.model.PlatformRiskResult;
import com.wrlus.seciot.platform.service.PlatformRiskServiceImpl;
import com.wrlus.seciot.util.exception.FileUploadException;
import com.wrlus.seciot.util.exception.ReasonEnum;
import com.wrlus.seciot.util.exception.RootException;
import com.wrlus.seciot.util.os.OSUtil;
import com.wrlus.seciot.waf.XSSProtect;

@Controller
@RequestMapping("/android")
public class AndroidController {
	private static Logger log = LogManager.getLogger();
	@Autowired
	private AndroidServiceImpl androidService;
	@Autowired
	private PlatformRiskServiceImpl platformRiskService;
	@Autowired
	private DeviceServiceImpl deviceService;
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
		path = path.replace("WEB-INF/classes/", "attach/uploads/apk/"+UUID.randomUUID().toString()+"/");
		if (OSUtil.isWindows()) {
			path = OSUtil.escapeUnixSeparator(path);
		}
		try {
//			保存上传文件
			File apkFile = this.resolveUploadFile((MultipartHttpServletRequest) request, path);
			ApkInfo apkInfo = androidService.getApkInfo(apkFile);
			apkInfo.setSize(apkFile.length());
			String[] permissions = androidService.getAndroidPermissions(apkInfo);
			List<PlatformRiskDao> platformRisks = platformRiskService.getPlatformRiskByCategory("Android");
			List<PlatformRiskResult> platformRiskResults = androidService.checkApkPlatformRisks(apkInfo, platformRisks.toArray(new PlatformRiskDao[0]));;
//			清除绝对路径信息，防止路径泄露
			apkInfo.setManifestFile("");
			apkInfo.setNdkLibPath("");
			apkInfo.setResourcesPath("");
			apkInfo.setSourcesPath("");
			apkInfo.setPath("");
//			返回状态码
			data.put("status", 0);
//			返回状态说明字符串
			data.put("reason", "OK");
//			返回APK信息
			data.put("apk_info", apkInfo);
//			返回APK所需权限
			data.put("apk_permission", permissions);
//			返回APK平台风险详情
			data.put("apk_platform_risk", platformRiskResults);
			AndroidHistoryDao androidHistory = new AndroidHistoryDao();
			androidHistory.setId(UUID.randomUUID().toString());
			androidHistory.setApkinfo(apkInfo);
			androidHistory.setApkpermission(permissions);
			androidHistory.setApkplatformrisk(platformRiskResults);
			historyService.addAndroidHistory(androidHistory);
			HistoryDao history = new HistoryDao();
			history.setId(UUID.randomUUID().toString());
			history.setName("AndroidStatic-"+apkFile.getName());
			history.setTarget(apkFile.getName());
			history.setType("android-static");
			history.setUser(getAuthenticatedUsername());
			history.setDate(new Date(new java.util.Date().getTime()));
			history.setDetailid(androidHistory.getId());
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
	
	@ResponseBody
	@RequestMapping("/getProcessList")
	public Map<String, Object> getProcessList(
			@RequestParam("port") int port,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			String[] processes = androidService.getProcessList(port);
			data.put("status", 0);
			data.put("processes", processes);
			data.put("reason", ReasonEnum.SUCCESS.get());
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
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/monitoring")
	public Map<String, Object> monitoring(MonitoringParameter monitoringParameter,
			HttpServletRequest request, HttpServletResponse response) { 
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			String result = androidService.monitoringDevice(monitoringParameter);
			data.put("status", 0);
			data.put("reason", "OK");
			if (result.equals(HookResult.HOOK_SUCCESS)) {
				deviceService.updateDeviceBusyStatus(monitoringParameter.getClientId(), 1);
				data.put("monitoring_result", "动态检测已启动");
			} else if (result.equals(HookResult.HOOK_STOP)) {
				data.put("monitoring_result", "动态检测已中止");
			}
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
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/stop-monitoring")
	public Map<String, Object> stopMonitoring(
			@RequestParam("clientId") String clientId,
			@RequestParam("port")  int port,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			String result = androidService.stopMonitoringDevice(port);
			data.put("status", 0);
			data.put("reason", "OK");
			if (result.equals(HookResult.HOOK_STOP)) {
				deviceService.updateDeviceBusyStatus(clientId, 0);
				data.put("monitoring_result", "动态检测已中止");
			}
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
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/refresh-frida-log")
	public Map<String, Object> refreshFridaLog(
			@RequestParam("port") int port, 
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data=new HashMap<String, Object>();
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		if (OSUtil.isWindows()) {
			path = path.replace("file:/", "");
		} else {
			path = path.replace("file:", "");
		}
		path = path.replace("WEB-INF/classes/", "WEB-INF/python/android_injection/hook_log/");
		if (OSUtil.isWindows()) {
			path = OSUtil.escapeUnixSeparator(path);
		}
		try {
			Thread.sleep(1000);
			BufferedReader logReader = new BufferedReader(new FileReader(path + "Host127.0.0.1:"+port+".log"));
			StringBuilder logFileContents = new StringBuilder();
			String line;
			while (( line = logReader.readLine() ) != null) {
				logFileContents.append(line);
				logFileContents.append("\n");
			}
			logReader.close();
			data.put("status", 0);
			data.put("reason", "OK");
			data.put("log", logFileContents);
		} catch (Exception e) {
			log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			data.put("status", -1);
			data.put("reason", ReasonEnum.FRIDA_ERROR.get());
		}
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/custom-injection")
	public Map<String, Object> customInjection(
			@RequestParam("port") int port,
			@RequestParam("process") String process,
			HttpServletRequest request, HttpServletResponse response) { 
		Map<String, Object> data=new HashMap<String, Object>();
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		if (OSUtil.isWindows()) {
			path = path.replace("file:/", "");
		} else {
			path = path.replace("file:", "");
		}
		path = path.replace("WEB-INF/classes/", "attach/uploads/js/"+UUID.randomUUID().toString()+"/");
		if (OSUtil.isWindows()) {
			path = OSUtil.escapeUnixSeparator(path);
		}
		try {
			File jsFile = this.resolveUploadFile((MultipartHttpServletRequest) request, path);
			String result = androidService.customInjection(port, process, jsFile);
//			返回状态码
			data.put("status", 0);
//			返回状态说明字符串
			data.put("reason", "OK");
			data.put("monitoring_result", result);
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
		return data;
	}
	
	public File resolveUploadFile(MultipartHttpServletRequest multipartRequest, String path) throws FileUploadException {
		try {
			MultipartFile multipartFile = multipartRequest.getFile("file");
			new File(path).mkdirs();
			String originalFilename = multipartFile.getOriginalFilename();
			if (!originalFilename.endsWith(".apk")) {
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


