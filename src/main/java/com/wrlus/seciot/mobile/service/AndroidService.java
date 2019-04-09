package com.wrlus.seciot.mobile.service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.wrlus.seciot.common.model.PlatformRiskDao;
import com.wrlus.seciot.mobile.model.ApkInfoModel;
import com.wrlus.seciot.mobile.model.ApkRiskReportModel;
import com.wrlus.seciot.pysocket.model.PythonException;

public interface AndroidService {
	public ApkInfoModel getApkInfo(File apkFile) throws IOException, PythonException;
	public String[] getAndroidPermissions(File manifestFile) throws IOException, PythonException;
	public Map<PlatformRiskDao, Boolean> checkApkPlatformRisks(PlatformRiskDao[] platformRisks) throws IOException, PythonException;
	public ApkRiskReportModel getApkRiskReport();
}
