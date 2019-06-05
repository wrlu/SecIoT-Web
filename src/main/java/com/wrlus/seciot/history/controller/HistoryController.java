package com.wrlus.seciot.history.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.wrlus.seciot.history.model.AndroidHistoryDao;
import com.wrlus.seciot.history.model.AppleiOSHistoryDao;
import com.wrlus.seciot.history.model.FwHistoryDao;
import com.wrlus.seciot.history.model.HistoryDao;
import com.wrlus.seciot.history.service.HistoryServiceImpl;
import com.wrlus.seciot.util.exception.ReasonEnum;

@Controller
@RequestMapping("/history")
public class HistoryController {
	private static Logger log = LogManager.getLogger();
	@Autowired
	private HistoryServiceImpl historyService;
	
	@ResponseBody
	@RequestMapping("/getHistoryAll")
	public Map<String, Object> getHistoryAll(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		try {
			List<HistoryDao> historyList = historyService.getHistoryAll();
			String username = getAuthenticatedUsername();
			for (HistoryDao history : historyList) {
				if (!history.getUser().equals(username)) {
					historyList.remove(history);
				}
			}
			data.put("status", 0);
			data.put("resaon", ReasonEnum.SUCCESS.get());
			data.put("history_list", historyList);
		} catch (Exception e) {
			log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			data.put("status", -1);
			data.put("reason", ReasonEnum.INVALID_PARAM.get());
		}
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/getHistoryByType")
	public Map<String, Object> getHistoryByType(@RequestParam("type") String type, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		try {
			List<HistoryDao> historyList = historyService.getHistoryByType(type);
			String username = getAuthenticatedUsername();
			for (HistoryDao history : historyList) {
				if (!history.getUser().equals(username)) {
					historyList.remove(history);
				}
			}
			data.put("status", 0);
			data.put("resaon", ReasonEnum.SUCCESS.get());
			data.put("history_list", historyList);
		} catch (Exception e) {
			log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			data.put("status", -1);
			data.put("reason", ReasonEnum.INVALID_PARAM.get());
		}
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/getFwHistoryById")
	public Map<String, Object> getFwHistoryById(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		try {
			FwHistoryDao history = historyService.getFwHistoryById(id).get(0);
			data.put("status", 0);
			data.put("reason", ReasonEnum.SUCCESS.get());
			data.put("fw_info", history.getFwinfoRaw());
			data.put("fw_lib", history.getFwlibRaw());
			data.put("fw_lib_risk", history.getFwlibriskRaw());
			data.put("fw_platform_risk", history.getFwplatformriskRaw());
		} catch (Exception e) {
			log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			data.put("status", -1);
			data.put("reason", ReasonEnum.INVALID_PARAM.get());
		}
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/getAndroidHistoryById")
	public Map<String, Object> getAndroidHistoryById(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		try {
			AndroidHistoryDao history = historyService.getAndroidHistoryById(id).get(0);
			data.put("status", 0);
			data.put("reason", ReasonEnum.SUCCESS.get());
			data.put("apk_info", history.getApkinfoRaw());
			data.put("apk_permission", history.getApkpermissionRaw());
			data.put("apk_platform_risk", history.getApkplatformriskRaw());
		} catch (Exception e) {
			log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			data.put("status", -1);
			data.put("reason", ReasonEnum.INVALID_PARAM.get());
		}
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/getAppleiOSHistoryById")
	public Map<String, Object> getAppleiOSHistoryById(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		try {
			AppleiOSHistoryDao history = historyService.getAppleiOSHistoryById(id).get(0);
			data.put("status", 0);
			data.put("reason", ReasonEnum.SUCCESS.get());
			data.put("ipa_info", history.getIpainfoRaw());
			data.put("ipa_permission", history.getIpapermissionRaw());
			data.put("ipa_platform_risk", history.getIpaplatformriskRaw());
		} catch (Exception e) {
			log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			data.put("status", -1);
			data.put("reason", ReasonEnum.INVALID_PARAM.get());
		}
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public Map<String, Object> edit(@RequestParam("id") String id, @RequestParam("name") String name, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		try {
			historyService.updateHistoryName(id, name);
			data.put("status", 0);
			data.put("reason", ReasonEnum.SUCCESS.get());
		} catch (Exception e) {
			log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			data.put("status", -1);
			data.put("reason", ReasonEnum.INVALID_PARAM.get());
		}
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		try {
			HistoryDao history = historyService.getHistoryById(id).get(0);
			String type = history.getType();
			if (type.equals("firmware-static")) {
				historyService.deleteFwHistory(history.getDetailid());
			} else if (type.equals("android-static")) {
				historyService.deleteAndroidHistory(history.getDetailid());
			} else if (type.equals("ios-static")) {
				historyService.deleteAppleiOSHistory(history.getDetailid());
			}
			historyService.deleteHistory(id);
			data.put("status", 0);
			data.put("reason", ReasonEnum.SUCCESS.get());
		} catch (Exception e) {
			log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			data.put("status", -1);
			data.put("reason", ReasonEnum.INVALID_PARAM.get());
		}
		return data;
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
