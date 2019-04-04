package com.wrlus.seciot.fw.service;

import java.io.File;

import com.wrlus.seciot.fw.model.FwInfoModel;
import com.wrlus.seciot.fw.model.FwRiskReportModel;
import com.wrlus.seciot.fw.model.FwThirdLibraryModel;

public interface FwService {
	public FwInfoModel getFwInfo(File fwFile);
	public File getFwRootDirectory(FwInfoModel fwInfoModel);
	public FwThirdLibraryModel getFwThirdLibrary(String libName);
	public FwRiskReportModel getFwRiskReport();
}
