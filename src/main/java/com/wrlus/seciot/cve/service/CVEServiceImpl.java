package com.wrlus.seciot.cve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wrlus.seciot.cve.dao.CVEMapper;
import com.wrlus.seciot.cve.model.CVEDao;

@Service
public class CVEServiceImpl implements CVEService {
	
	@Autowired
	private CVEMapper dao;

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

}
