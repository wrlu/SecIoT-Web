package com.wrlus.seciot.common.service;

import java.util.List;

import com.wrlus.seciot.common.model.CVEDao;
import com.wrlus.seciot.common.model.PlatformRiskDao;
import com.wrlus.seciot.common.model.ThirdLibraryDao;

public interface CommonService {
	public List<CVEDao> getCVEAll();
	public List<CVEDao> getCVEByNum(String cvenumber);
	public List<CVEDao> getCVEByCategory(String category);
	public List<PlatformRiskDao> getPlatformRiskAll();
	public List<PlatformRiskDao> getPlatformRiskById(long id);
	public List<PlatformRiskDao> getPlatformRiskByCategory(String category);
	public List<ThirdLibraryDao> getThirdLibraryAll();
	public List<ThirdLibraryDao> getThirdLibraryById(long id);
}
