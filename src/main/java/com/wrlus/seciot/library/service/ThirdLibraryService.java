package com.wrlus.seciot.library.service;

import java.util.List;

import com.wrlus.seciot.library.model.ThirdLibraryDao;
import com.wrlus.seciot.library.model.ThirdLibraryRiskDao;

public interface ThirdLibraryService {
	public List<ThirdLibraryDao> getThirdLibraryAll();
	public List<ThirdLibraryDao> getThirdLibraryById(String id);
	public List<ThirdLibraryRiskDao> getThirdLibraryRiskByLibInfo(String libname, String libversion);
	public List<ThirdLibraryRiskDao> getThirdLibraryRiskById(String id);
}
