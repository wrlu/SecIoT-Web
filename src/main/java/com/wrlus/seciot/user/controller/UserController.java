package com.wrlus.seciot.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wrlus.seciot.user.model.UserDao;
import com.wrlus.seciot.user.service.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@ResponseBody
	@RequestMapping("/signup")
	public Map<String, Object> signup(UserDao user, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(0);
		userService.addUser(user);
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/enable")
	public Map<String, Object> enable(UserDao user, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/disable")
	public Map<String, Object> disable(UserDao user, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/grant")
	public Map<String, Object> grant(
			@RequestParam("username") String username,
			@RequestParam("authority") String authority, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/revoke")
	public Map<String, Object> revoke(@RequestParam("username") String username, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<>();
		return data;
	}
}
