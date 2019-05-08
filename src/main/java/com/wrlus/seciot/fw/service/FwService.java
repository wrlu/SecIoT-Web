package com.wrlus.seciot.fw.service;

import java.io.File;
import java.util.List;

import com.wrlus.seciot.fw.model.FwInfo;
import com.wrlus.seciot.library.model.ThirdLibrary;
import com.wrlus.seciot.platform.model.PlatformRiskDao;
import com.wrlus.seciot.platform.model.PlatformRiskResult;
import com.wrlus.seciot.util.exception.PythonException;

public interface FwService {
	public FwInfo getFwInfo(File fwFile) throws PythonException;
	public File getFwRootDirectory(FwInfo fwInfoModel) throws PythonException;
	public ThirdLibrary getFwThirdLibrary(FwInfo fwInfo, String libName) throws PythonException;
	public List<PlatformRiskResult> checkFwPlatformRisks(FwInfo fwInfo, PlatformRiskDao[] platformRisks) throws PythonException;
}
