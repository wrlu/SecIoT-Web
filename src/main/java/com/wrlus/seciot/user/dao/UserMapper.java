package com.wrlus.seciot.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wrlus.seciot.user.model.UserDao;

public interface UserMapper {
	
	@Select("select * from users;")
	public List<UserDao> getAllUser();
	@Select("select * from users where username = #{username};")
	public List<UserDao> getUserByUsername(@Param("username") String username);
	@Insert("insert into users values(#{username}, #{password}, #{enabled});")
	public int addUser(UserDao user);
	@Update("update users set password = #{password} where username = #{username};")
	public int updateUserPassword(@Param("username") String username, @Param("password") String password);
	@Update("update users set enabled = #{enabled} where username = #{username};")
	public int updateUserEnable(@Param("username") String username, @Param("enabled") int enabled);
	@Insert("insert into authorities values(#{username}, #{authority});")
	public int addUserRole(@Param("username") String username, @Param("authority") String authority);
	@Update("update authorities set authority = #{authority} where username = #{username};")
	public int updateUserRole(@Param("username") String username, @Param("authority") String authority);
	@Delete("delete from authorities where username = #{username};")
	public int deleteUserRole(@Param("username") String username);
	@Delete("delete from users where username = #{username};")
	public int deleteUser(@Param("username") String username);
}
