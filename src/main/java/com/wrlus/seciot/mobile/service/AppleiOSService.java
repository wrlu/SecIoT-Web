package com.wrlus.seciot.mobile.service;

import java.io.File;
import java.util.List;

import com.wrlus.seciot.mobile.model.IpaInfo;
import com.wrlus.seciot.platform.model.PlatformRiskDao;
import com.wrlus.seciot.platform.model.PlatformRiskResult;
import com.wrlus.seciot.util.exception.PythonException;

public interface AppleiOSService {
	public IpaInfo getIpaInfo(File ipaFile) throws PythonException;
	public String[] getiOSPermissions(IpaInfo ipaInfo) throws PythonException;
	public List<PlatformRiskResult> checkIpaPlatformRisks(IpaInfo ipaInfo, PlatformRiskDao[] platformRisks) throws PythonException;;
}
