package com.wrlus.seciot.fw.service;

import java.io.File;
import java.util.Map;

import com.wrlus.seciot.fw.model.FwInfoModel;
import com.wrlus.seciot.fw.model.FwLibraryRiskModel;
import com.wrlus.seciot.fw.model.FwRiskReportModel;
import com.wrlus.seciot.fw.model.FwThirdLibraryModel;
import com.wrlus.seciot.model.PlatformRiskModel;

public interface FwService {
	public FwInfoModel getFwInfo(File fwFile);
	public File getFwRootDirectory(FwInfoModel fwInfoModel);
	public FwThirdLibraryModel getFwThirdLibrary(String libName);
	public Map<FwLibraryRiskModel, Boolean> checkFwLibraryRisks(FwLibraryRiskModel[] fwLibraryRisks);
	public Map<PlatformRiskModel, Boolean> checkFwPlatformRisks(PlatformRiskModel[] platformRisks);
	public FwRiskReportModel getFwRiskReport();
}
