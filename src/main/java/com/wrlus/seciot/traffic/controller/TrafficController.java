package com.wrlus.seciot.traffic.controller;

import java.io.File;
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
import com.wrlus.seciot.traffic.model.ConnectionDetails;
import com.wrlus.seciot.traffic.service.TrafficServiceImpl;
import com.wrlus.seciot.util.exception.FileUploadException;
import com.wrlus.seciot.util.exception.ReasonEnum;
import com.wrlus.seciot.util.exception.RootException;
import com.wrlus.seciot.util.os.OSUtil;

@Deprecated
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
			data.put("status", -1);
			data.put("reason", "输入的参数有误");
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
			data.put("status", 0);
//			返回设备连接对象详情
			data.put("tfc_dev_conn_details", devConnDetails);
//			返回控制端连接对象详情
			data.put("tfc_mobile_conn_details", mobileConnDetails);
//			返回状态说明字符串
			data.put("reason", "OK");
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
