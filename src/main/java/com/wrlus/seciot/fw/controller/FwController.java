package com.wrlus.seciot.fw.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrlus.seciot.fw.model.FwInfo;
import com.wrlus.seciot.fw.service.FwServiceImpl;
import com.wrlus.seciot.library.model.ThirdLibraryDao;
import com.wrlus.seciot.library.model.ThirdLibrary;
import com.wrlus.seciot.library.model.ThirdLibraryRiskDao;
import com.wrlus.seciot.library.service.ThirdLibraryServiceImpl;
import com.wrlus.seciot.platform.model.PlatformRiskDao;
import com.wrlus.seciot.platform.model.PlatformRiskResult;
import com.wrlus.seciot.platform.service.PlatformRiskServiceImpl;
import com.wrlus.seciot.pysocket.model.PythonException;
import com.wrlus.seciot.util.OSUtil;
import com.wrlus.seciot.util.Status;

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
	
	@ResponseBody
	@RequestMapping("/analysis")
	public Map<String, Object> analysis(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data=new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		// Windows: file:/C:/******/SecIoT/WebContent/WEB-INF/classes/
		// *nix: file:/mnt/******/SecIoT/WEB-INF/classes/
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		System.out.println(path);
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
			log.debug("FwInfo: " + mapper.writeValueAsString(fwInfo));
//			提取固件（binwalk -Me），获得固件根路径
			File rootDir = fwService.getFwRootDirectory(fwInfo);
			fwInfo.setRootDir(rootDir.getAbsolutePath());
			log.debug("FwInfo: " + mapper.writeValueAsString(fwInfo));
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
					log.debug("LibraryRisks: "+mapper.writeValueAsString(libraryRisks));
					thirdLibraryRisks.put(library.getName(), libraryRisks);
				}
			}
//			获得所有Firmware类型的平台风险
			List<PlatformRiskDao> platformRisks = platformRiskService.getPlatformRiskByCategory("Firmware");
			List<PlatformRiskResult> platformRiskResults = fwService.checkFwPlatformRisks(fwInfo, platformRisks.toArray(new PlatformRiskDao[0]));
			log.debug("PlatformRisks: "+mapper.writeValueAsString(platformRiskResults));
//			清除绝对路径信息，防止路径泄露
			fwInfo.setPath("");
			fwInfo.setRootDir(fwInfo.getRootDir().split(".extracted")[1]);
//			返回状态码
			data.put("status", Status.SUCCESS);
//			返回状态说明字符串
			data.put("reason", "OK");
//			返回固件信息
			data.put("fw_info", fwInfo);
//			返回第三方库信息
			data.put("fw_lib", thirdLibraries);
//			返回每种第三方库的所有风险
			data.put("fw_lib_risk", thirdLibraryRisks);
//			返回平台风险详情
			data.put("fw_platform_risk", platformRiskResults);
		} catch (ClassCastException | NullPointerException e) {
			data.put("status", Status.FILE_UPD_ERROR);
			data.put("reason", "文件上传失败，错误代码："+Status.FILE_UPD_ERROR);
			log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
		} catch (PythonException e) {
			data.put("status", Status.PY_ERROR);
			data.put("reason", e.getLocalizedMessage());
			log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
		} catch (IllegalStateException e) {
			data.put("status", Status.FILE_UPD_ERROR);
			data.put("reason", "Python出现异常，错误代码："+Status.PY_ERROR);
			log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			data.put("status", Status.IO_ERROR);
			data.put("reason", "文件或Socket I/O错误，错误代码："+Status.IO_ERROR);
			log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	private File resolveUploadFile(MultipartHttpServletRequest multipartRequest, String path) throws IllegalStateException, IOException{
		MultipartFile multipartFile = multipartRequest.getFile("file");
		new File(path).mkdirs();
		File targetFile = new File(path + multipartFile.getOriginalFilename());
		multipartFile.transferTo(targetFile);
		return targetFile;
	}

}
