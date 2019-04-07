package com.wrlus.seciot.fw.service;

import java.io.File;
import java.util.Map;

import com.wrlus.seciot.fw.model.FwInfoModel;
import com.wrlus.seciot.fw.model.FwLibraryRiskModel;
import com.wrlus.seciot.fw.model.FwRiskReportModel;
import com.wrlus.seciot.fw.model.FwThirdLibraryModel;
import com.wrlus.seciot.model.PlatformRiskModel;
import com.wrlus.seciot.pysocket.PythonException;

public interface FwService {
	public FwInfoModel getFwInfo(String filename, File fwFile) throws PythonException;
	public File getFwRootDirectory(FwInfoModel fwInfoModel) throws PythonException;
	public FwThirdLibraryModel getFwThirdLibrary(FwInfoModel fwInfo, String libName) throws PythonException ;
	public Map<FwLibraryRiskModel, Boolean> checkFwLibraryRisks(FwInfoModel fwInfo, FwLibraryRiskModel[] fwLibraryRisks) throws PythonException ;
	public Map<PlatformRiskModel, Boolean> checkFwPlatformRisks(FwInfoModel fwInfo, PlatformRiskModel[] platformRisks) throws PythonException ;
	public FwRiskReportModel getFwRiskReport();
}
