package com.wrlus.seciot.device.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wrlus.seciot.device.model.DeviceDao;

@Repository
public interface DeviceMapper {
	public List<DeviceDao> getOnlineDevices();
	public List<DeviceDao> getDeviceByClientId(String clientId);
	public int insertDevice(DeviceDao deviceDao);
	public int updateDevice(boolean isOnline);
	public int deleteDevice(String clientId);
}
