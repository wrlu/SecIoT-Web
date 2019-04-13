package com.wrlus.seciot.platform.service;

import java.util.List;

import com.wrlus.seciot.platform.model.PlatformRiskDao;

public interface PlatformRiskService {
	public List<PlatformRiskDao> getPlatformRiskAll();
	public List<PlatformRiskDao> getPlatformRiskById(String id);
	public List<PlatformRiskDao> getPlatformRiskByCategory(String category);
}
