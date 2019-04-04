package com.wrlus.seciot.mobile.service;

import java.io.File;

import com.wrlus.seciot.mobile.model.IpaInfoModel;
import com.wrlus.seciot.mobile.model.IpaRiskReportModel;

public interface AppleiOSService {
	public IpaInfoModel getIpaInfo(File ipaFile);
	public File getIpaInfoPlistFile(IpaInfoModel ipaInfoModel);
	public String[] getiOSPermissions(File infoPlistFile);
	public IpaRiskReportModel getIpaRiskReport();
}
