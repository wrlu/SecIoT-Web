package com.wrlus.seciot.device.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wrlus.seciot.device.model.DeviceDao;

@Controller
@RequestMapping("/device")
public class DeviceController {
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(DeviceDao deviceDao, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(DeviceDao deviceDao, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		return data;
	}
}
