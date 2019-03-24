package com.wrlus.seciot.dashboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wrlus.seciot.util.Status;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@ResponseBody
	@RequestMapping("/refresh")
	public Map<String, Object> dashboardRefresh(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("Status", Status.SUCCESS);
		return data;
	}
}
