package com.wrlus.seciot.mobile.service;

import java.io.File;
import java.util.Map;

import com.wrlus.seciot.common.model.PlatformRiskDao;
import com.wrlus.seciot.mobile.model.IpaInfoModel;

public interface AppleiOSService {
	public IpaInfoModel getIpaInfo(File ipaFile);
	public String[] getiOSPermissions(File infoPlistFile);
	public Map<PlatformRiskDao, Boolean> checkIpaPlatformRisks(PlatformRiskDao[] platformRisks);
}
