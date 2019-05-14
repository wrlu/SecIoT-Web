package com.wrlus.seciot.device.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wrlus.seciot.device.dao.DeviceMapper;
import com.wrlus.seciot.device.model.DeviceDao;

@Service
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	private DeviceMapper dao;

	@Override
	public List<DeviceDao> getOnlineDevices() {
		return dao.getOnlineDevices();
	}

	@Override
	public List<DeviceDao> getDeviceByClientId(String clientId) {
		return dao.getDeviceByClientId(clientId);
	}

	@Override
	public int insertDevice(DeviceDao deviceDao) {
		return dao.insertDevice(deviceDao);
	}

	@Override
	public int updateDevice(DeviceDao deviceDao) {
		return dao.updateDevice(deviceDao);
	}

	@Override
	public int deleteDevice(String clientId) {
		return dao.deleteDevice(clientId);
	}

}
