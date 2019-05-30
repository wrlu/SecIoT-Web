package com.wrlus.seciot.user.service;

import java.util.List;

import com.wrlus.seciot.user.model.UserDao;

public interface UserService {
	public List<UserDao> getAllUser();
	public List<UserDao> getUserByUsername(String username);
	public int addUser(UserDao user);
	public int updateUserPassword(String username, String password);
	public int updateUserEnable(String username, int enabled);
	public int addUserRole(String username, String authority);
	public int updateUserRole(String username, String authority);
	public int deleteUserRole(String username);
	public int deleteUser(String username);
}
