package com.wrlus.seciot.mobile.service;

import java.io.File;
import java.util.Map;

import com.wrlus.seciot.mobile.model.IpaInfo;
import com.wrlus.seciot.platform.model.PlatformRiskDao;

public interface AppleiOSService {
	public IpaInfo getIpaInfo(File ipaFile);
	public String[] getiOSPermissions(File infoPlistFile);
	public Map<PlatformRiskDao, Boolean> checkIpaPlatformRisks(PlatformRiskDao[] platformRisks);
}
