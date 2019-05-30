package com.wrlus.seciot.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wrlus.seciot.user.dao.UserMapper;
import com.wrlus.seciot.user.model.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper dao;
	
	@Override
	public List<UserDao> getAllUser() {
		return dao.getAllUser();
	}

	@Override
	public List<UserDao> getUserByUsername(String username) {
		return dao.getUserByUsername(username);
	}

	@Override
	public int addUser(UserDao user) {
		return dao.addUser(user);
	}

	@Override
	public int updateUserPassword(String username, String password) {
		return dao.updateUserPassword(username, password);
	}
	
	@Override
	public int updateUserEnable(String username, int enabled) {
		return dao.updateUserEnable(username, enabled);
	}

	@Override
	public int addUserRole(String username, String authority) {
		return dao.addUserRole(username, authority);
	}

	@Override
	public int updateUserRole(String username, String authority) {
		return dao.updateUserRole(username, authority);
	}

	@Override
	public int deleteUserRole(String username) {
		return dao.deleteUserRole(username);
	}
	
	@Override
	public int deleteUser(String username) {
		return dao.deleteUser(username);
	}

}
