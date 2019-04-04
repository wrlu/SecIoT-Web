package com.wrlus.seciot.mobile.service;

import java.io.File;
import java.util.Map;

import com.wrlus.seciot.mobile.model.ApkInfoModel;
import com.wrlus.seciot.mobile.model.ApkRiskReportModel;
import com.wrlus.seciot.model.PlatformRiskModel;

public interface AndroidService {
	public ApkInfoModel getApkInfo(File apkFile);
	public File getAndroidManifestFile(ApkInfoModel apkInfoModel);
	public String[] getAndroidPermissions(File manifestFile);
	public File getApkSmaliSourceRootDirectory(ApkInfoModel apkInfoModel);
	public File getApkJavaSourceRootDirectory(ApkInfoModel apkInfoModel);
	public File getApkNdkLibraryDirectory(ApkInfoModel apkInfoModel, String abiName);
	public Map<PlatformRiskModel, Boolean> checkApkPlatformRisks(PlatformRiskModel[] platformRisks);
	public ApkRiskReportModel getApkRiskReport();
}
