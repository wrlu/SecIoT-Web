package com.wrlus.seciot.device.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.wrlus.seciot.device.model.DeviceDao;

@Repository
public interface DeviceMapper {
	@Select("select * from device where online = 1;")
	public List<DeviceDao> getOnlineDevices();
	
	@Select("select * from device where clientid = #{clientId};")
	public List<DeviceDao> getDeviceByClientId(@Param("clientId") String clientId);
	
	@Insert("insert into device values (#{clientid}, #{devicename}, #{version}, #{apilevel}, #{agentver}, #{port}, #{online}, 0);")
	public int insertDevice(DeviceDao deviceDao);
	
	@Update("update device set version = #{version}, apilevel = #{apilevel}, agentver = #{agentver}, port = #{port}, online = #{online} where clientid = #{clientid}; ")
	public int updateDevice(DeviceDao deviceDao);
	
	@Update("update device set busy = #{busy} where clientId = #{clientId};")
	public int updateDeviceBusyStatus(@Param("clientId") String clientId, @Param("busy") int busy);
	
	@Delete("delete from device where clientid = #{clientId};")
	public int deleteDevice(String clientId);
}
