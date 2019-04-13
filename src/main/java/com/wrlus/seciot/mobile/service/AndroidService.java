package com.wrlus.seciot.mobile.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.wrlus.seciot.mobile.model.ApkInfo;
import com.wrlus.seciot.platform.model.PlatformRiskDao;
import com.wrlus.seciot.platform.model.PlatformRiskResult;
import com.wrlus.seciot.pysocket.model.PythonException;

public interface AndroidService {
	public ApkInfo getApkInfo(File apkFile) throws IOException, PythonException;
	public String[] getAndroidPermissions(File manifestFile) throws IOException, PythonException;
	public List<PlatformRiskResult> checkApkPlatformRisks(ApkInfo apkInfo, PlatformRiskDao[] platformRisks) throws IOException, PythonException;
}
