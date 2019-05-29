package com.wrlus.seciot.device.service;

import java.util.List;

import com.wrlus.seciot.device.model.DeviceDao;

public interface DeviceService {
	public List<DeviceDao> getOnlineDevices();
	public List<DeviceDao> getDeviceByClientId(String clientId);
	public int insertDevice(DeviceDao deviceDao);
	public int updateDevice(DeviceDao deviceDao);
	public int deleteDevice(String clientId);
	public int updateDeviceBusyStatus(String clientId, int busy);
}
