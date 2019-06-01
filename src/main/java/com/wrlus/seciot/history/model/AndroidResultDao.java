package com.wrlus.seciot.history.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrlus.seciot.mobile.model.ApkInfo;
import com.wrlus.seciot.platform.model.PlatformRiskResult;

public class AndroidResultDao {
	private ApkInfo apkinfo;
	private String[] apkpermission;
	private List<PlatformRiskResult> apkplatformrisk;
	private static ObjectMapper mapper = new ObjectMapper();
	public String getApkinfo() {
		try {
			return mapper.writeValueAsString(apkinfo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void setApkinfo(String apkinfo) {
		try {
			this.apkinfo = mapper.readValue(apkinfo, ApkInfo.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getApkpermission() {
		try {
			return mapper.writeValueAsString(apkpermission);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void setApkpermission(String apkpermission) {
		try {
			this.apkpermission = mapper.readValue(apkpermission, String[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getApkplatformrisk() {
		try {
			return mapper.writeValueAsString(apkplatformrisk);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public void setApkplatformrisk(String apkplatformrisk) {
		try {
			this.apkplatformrisk = mapper.readValue(apkplatformrisk, List.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
