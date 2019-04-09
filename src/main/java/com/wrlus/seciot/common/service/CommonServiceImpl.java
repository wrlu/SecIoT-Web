package com.wrlus.seciot.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wrlus.seciot.common.dao.CommonMapper;
import com.wrlus.seciot.common.model.CVEDao;
import com.wrlus.seciot.common.model.PlatformRiskDao;
import com.wrlus.seciot.common.model.ThirdLibraryDao;

@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	private CommonMapper dao;

	@Override
	public List<CVEDao> getCVEAll() {
		return dao.getCVEAll();
	}

	@Override
	public List<CVEDao> getCVEByNum(String cvenumber) {
		return dao.getCVEByNum(cvenumber);
	}

	@Override
	public List<CVEDao> getCVEByCategory(String category) {
		return dao.getCVEByCategory(category);
	}

	@Override
	public List<PlatformRiskDao> getPlatformRiskAll() {
		return dao.getPlatformRiskAll();
	}

	@Override
	public List<PlatformRiskDao> getPlatformRiskById(long id) {
		return dao.getPlatformRiskById(id);
	}

	@Override
	public List<PlatformRiskDao> getPlatformRiskByCategory(String category) {
		return dao.getPlatformRiskByCategory(category);
	}

	@Override
	public List<ThirdLibraryDao> getThirdLibraryAll() {
		return dao.getThirdLibraryAll();
	}

	@Override
	public List<ThirdLibraryDao> getThirdLibraryById(long id) {
		return dao.getThirdLibraryById(id);
	}

}
