package com.wrlus.seciot.traffic.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
import com.wrlus.seciot.pysocket.model.PythonException;
import com.wrlus.seciot.traffic.model.ConnectionDetails;
import com.wrlus.seciot.traffic.service.TrafficServiceImpl;
import com.wrlus.seciot.util.OSUtil;
import com.wrlus.seciot.util.Status;

@Controller
@RequestMapping("/traffic")
public class TrafficController {
	private static Logger log = LogManager.getLogger();
	@Autowired
	private TrafficServiceImpl trafficService;
	
	@ResponseBody
	@RequestMapping("/analysis")
	public Map<String, Object> analysis(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data=new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		String devIp = request.getParameter("deviceIp");
		String phoneIp = request.getParameter("mobileIp");
		if (devIp == null || phoneIp == null || devIp.equals("") || phoneIp.equals("")) {
			data.put("status", Status.PARAMETER_ERROR);
			data.put("reason", "参数错误，错误代码："+Status.PARAMETER_ERROR);
			return data;
		}
		// Windows: file:/C:/******/SecIoT/WebContent/WEB-INF/classes/
		// *nix: file:/mnt/******/SecIoT/WEB-INF/classes/
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		log.debug("Server Path: "+path);
		if (OSUtil.isWindows()) {
			path = path.replace("file:/", "");
		} else {
			path = path.replace("file:", "");
		}
		path = path.replace("WEB-INF/classes/", "attach/uploads/pcap/"+UUID.randomUUID().toString()+"/");
		if (OSUtil.isWindows()) {
			path = OSUtil.escapeUnixSeparator(path);
		}
		try {
			File pcapFile = this.resolveUploadFile((MultipartHttpServletRequest) request, path);
			ConnectionDetails devConnDetails = trafficService.getConnectionDetails(pcapFile, devIp);
			log.debug("devConnDetails: "+mapper.writeValueAsString(devConnDetails));
			ConnectionDetails mobileConnDetails = trafficService.getConnectionDetails(pcapFile, phoneIp);
			log.debug("mobileConnDetails: "+mapper.writeValueAsString(mobileConnDetails));
//			返回状态码
			data.put("status", Status.SUCCESS);
//			返回设备连接对象详情
			data.put("tfc_dev_conn_details", devConnDetails);
//			返回控制端连接对象详情
			data.put("tfc_mobile_conn_details", mobileConnDetails);
//			返回状态说明字符串
			data.put("reason", "OK");
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
