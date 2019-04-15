package com.wrlus.seciot.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wrlus.seciot.library.dao.ThirdLibraryMapper;
import com.wrlus.seciot.library.model.ThirdLibraryDao;
import com.wrlus.seciot.library.model.ThirdLibraryRiskDao;

@Service
public class ThirdLibraryServiceImpl implements ThirdLibraryService {
	@Autowired
	private ThirdLibraryMapper dao;

	@Override
	public List<ThirdLibraryDao> getThirdLibraryAll() {
		return dao.getThirdLibraryAll();
	}

	@Override
	public List<ThirdLibraryDao> getThirdLibraryById(String id) {
		return dao.getThirdLibraryById(id);
	}

	@Override
	public List<ThirdLibraryRiskDao> getThirdLibraryRiskByLibInfo(String libname, String libversion) {
		return dao.getThirdLibraryRiskByLibInfo(libname, libversion);
	}

	@Override
	public List<ThirdLibraryRiskDao> getThirdLibraryRiskById(String id) {
		return dao.getThirdLibraryRiskById(id);
	}

}
