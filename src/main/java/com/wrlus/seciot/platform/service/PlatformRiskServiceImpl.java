package com.wrlus.seciot.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wrlus.seciot.platform.dao.PlatformRiskMapper;
import com.wrlus.seciot.platform.model.PlatformRiskDao;

public class PlatformRiskServiceImpl implements PlatformRiskService {
	@Autowired
	private PlatformRiskMapper dao;
	
	@Override
	public List<PlatformRiskDao> getPlatformRiskAll() {
		return dao.getPlatformRiskAll();
	}

	@Override
	public List<PlatformRiskDao> getPlatformRiskById(String id) {
		return dao.getPlatformRiskById(id);
	}

	@Override
	public List<PlatformRiskDao> getPlatformRiskByCategory(String category) {
		return dao.getPlatformRiskByCategory(category);
	}
}
