package com.wrlus.seciot.fw.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.wrlus.seciot.fw.model.FwInfoModel;
import com.wrlus.seciot.fw.model.FwLibraryRiskModel;
import com.wrlus.seciot.fw.model.FwRiskReportModel;
import com.wrlus.seciot.fw.model.FwThirdLibraryModel;
import com.wrlus.seciot.model.PlatformRiskModel;
import com.wrlus.seciot.pysocket.model.PythonException;

public interface FwService {
	public FwInfoModel getFwInfo(String filename, File fwFile) throws IOException, PythonException;
	public File getFwRootDirectory(FwInfoModel fwInfoModel) throws IOException, PythonException;
	public FwThirdLibraryModel getFwThirdLibrary(FwInfoModel fwInfo, String libName) throws IOException, PythonException;
	public Map<FwLibraryRiskModel, Boolean> checkFwLibraryRisks(FwInfoModel fwInfo, FwLibraryRiskModel[] fwLibraryRisks) throws IOException, PythonException;
	public Map<PlatformRiskModel, Boolean> checkFwPlatformRisks(FwInfoModel fwInfo, PlatformRiskModel[] platformRisks) throws IOException, PythonException;
	public FwRiskReportModel getFwRiskReport();
}
