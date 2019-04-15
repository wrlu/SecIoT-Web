package com.wrlus.seciot.fw.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.wrlus.seciot.fw.model.FwInfo;
import com.wrlus.seciot.library.model.ThirdLibrary;
import com.wrlus.seciot.library.model.ThirdLibraryRiskDao;
import com.wrlus.seciot.library.model.ThirdLibraryRiskResult;
import com.wrlus.seciot.platform.model.PlatformRiskDao;
import com.wrlus.seciot.platform.model.PlatformRiskResult;
import com.wrlus.seciot.pysocket.model.PythonException;

public interface FwService {
	public FwInfo getFwInfo(File fwFile) throws IOException, PythonException;
	public File getFwRootDirectory(FwInfo fwInfoModel) throws IOException, PythonException;
	public ThirdLibrary getFwThirdLibrary(FwInfo fwInfo, String libName) throws IOException, PythonException;
	public List<ThirdLibraryRiskResult> checkFwLibraryRisks(FwInfo fwInfo, ThirdLibraryRiskDao[] fwLibraryRisks) throws IOException, PythonException;
	public List<PlatformRiskResult> checkFwPlatformRisks(FwInfo fwInfo, PlatformRiskDao[] platformRisks) throws IOException, PythonException;
}
