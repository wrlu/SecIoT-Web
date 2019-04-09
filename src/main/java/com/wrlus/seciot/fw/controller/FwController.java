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
import com.wrlus.seciot.common.model.ThirdLibraryModel;
import com.wrlus.seciot.fw.model.FwInfoModel;
import com.wrlus.seciot.fw.service.FwServiceImpl;
import com.wrlus.seciot.pysocket.model.PythonException;
import com.wrlus.seciot.util.OSUtil;
import com.wrlus.seciot.util.Status;

@Controller
@RequestMapping("/fw")
public class FwController {
	private static Logger log = LogManager.getLogger();
	@Autowired
	private FwServiceImpl fwService;
	
	@ResponseBody
	@RequestMapping("/analysis")
	public Map<String, Object> analysis(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data=new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		path = path.replace("file:/", "");
		path = path.replace("WEB-INF/classes/", "attach/uploads/firmware/"+UUID.randomUUID().toString().toUpperCase()+"/");
		if (OSUtil.isWindows()) {
			path = OSUtil.escapeUnixSeparator(path);
		}
		try {
//			保存上传文件
			File fwFile = this.resolveUploadFile((MultipartHttpServletRequest) request, path);
//			分析固件信息（binwalk）
			FwInfoModel fwInfo = fwService.getFwInfo(fwFile);
			log.debug("FwInfo: " + mapper.writeValueAsString(fwInfo));
//			提取固件（binwalk -Me），获得固件根路径
			File rootDir = fwService.getFwRootDirectory(fwInfo);
			fwInfo.setRootDir(rootDir.getAbsolutePath());
			log.debug("FwInfo: " + mapper.writeValueAsString(fwInfo));
			List<ThirdLibraryModel> fwThirdLibraries = new ArrayList<>();
//			获得OpenSSL版本
			String[] libnames = {"OpenSSL"};
			for (String libname : libnames) {
				ThirdLibraryModel lib = fwService.getFwThirdLibrary(fwInfo, libname);
				fwThirdLibraries.add(lib);
			}
			data.put("status", Status.SUCCESS);
			data.put("reason", "OK");
			data.put("fw_info", fwInfo);
			data.put("third_lib_info", fwThirdLibraries);
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
