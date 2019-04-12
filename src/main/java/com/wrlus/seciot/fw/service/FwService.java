package com.wrlus.seciot.fw.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.wrlus.seciot.common.model.ThirdLibraryModel;
import com.wrlus.seciot.common.model.ThirdLibraryRiskDao;
import com.wrlus.seciot.common.model.PlatformRiskDao;
import com.wrlus.seciot.fw.model.FwInfoModel;
import com.wrlus.seciot.pysocket.model.PythonException;

public interface FwService {
	public FwInfoModel getFwInfo(File fwFile) throws IOException, PythonException;
	public File getFwRootDirectory(FwInfoModel fwInfoModel) throws IOException, PythonException;
	public ThirdLibraryModel getFwThirdLibrary(FwInfoModel fwInfo, String libName) throws IOException, PythonException;
	public Map<ThirdLibraryRiskDao, Boolean> checkFwLibraryRisks(FwInfoModel fwInfo, ThirdLibraryRiskDao[] fwLibraryRisks) throws IOException, PythonException;
	public Map<PlatformRiskDao, Boolean> checkFwPlatformRisks(FwInfoModel fwInfo, PlatformRiskDao[] platformRisks) throws IOException, PythonException;
}
