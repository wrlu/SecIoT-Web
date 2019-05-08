package com.wrlus.seciot.cve.service;

import java.util.List;

import com.wrlus.seciot.cve.model.CVEDao;

public interface CVEService {
	public List<CVEDao> getCVEAll();
	public List<CVEDao> getCVEByNum(String cvenumber);
	public List<CVEDao> getCVEByCategory(String category);
}
