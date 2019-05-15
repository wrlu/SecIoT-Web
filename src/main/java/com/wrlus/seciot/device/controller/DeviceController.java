package com.wrlus.seciot.device.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wrlus.seciot.device.model.DeviceDao;
import com.wrlus.seciot.device.service.DeviceServiceImpl;
import com.wrlus.seciot.util.exception.ReasonEnum;

@Controller
@RequestMapping("/device")
public class DeviceController {
	private static Logger log = LogManager.getLogger();
	@Autowired
	private DeviceServiceImpl deviceService;
	
	@ResponseBody
	@RequestMapping("/getOnlineDevice")
	public Map<String, Object> getOnlineDevice(DeviceDao deviceDao, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		try {
			List<DeviceDao> devices = deviceService.getOnlineDevices();
			data.put("status", 0);
			data.put("reason", ReasonEnum.SUCCESS.get());
			data.put("devices", devices);
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
	@RequestMapping("/getDeviceById")
	public Map<String, Object> getDeviceById(@RequestParam("clientid") String clientId, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		try {
			List<DeviceDao> devices = deviceService.getDeviceByClientId(clientId);
			data.put("status", 0);
			data.put("reason", ReasonEnum.SUCCESS.get());
			data.put("devices", devices);
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
	@RequestMapping("/add")
	public Map<String, Object> add(DeviceDao deviceDao, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		try {
			int rows = deviceService.insertDevice(deviceDao);
			if (rows == 1) {
				data.put("status", 0);
				data.put("reason", ReasonEnum.SUCCESS.get());
			} else {
				data.put("status", -1);
				data.put("reason", ReasonEnum.CLIENT_ALREADY_EXISTS.get());
			}
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
	@RequestMapping("/update")
	public Map<String, Object> update(DeviceDao deviceDao, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		try {
			int rows = deviceService.updateDevice(deviceDao);
			if (rows == 1) {
				data.put("status", 0);
				data.put("reason", ReasonEnum.SUCCESS.get());
			} else {
				data.put("status", -1);
				data.put("reason", ReasonEnum.NO_SUCH_CLINET.get());
			}
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
}
