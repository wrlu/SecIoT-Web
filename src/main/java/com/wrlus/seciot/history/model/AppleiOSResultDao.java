package com.wrlus.seciot.history.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrlus.seciot.mobile.model.IpaInfo;
import com.wrlus.seciot.platform.model.PlatformRiskResult;

public class AppleiOSResultDao {
	private IpaInfo ipainfo;
	private String[] ipapermission;
	private List<PlatformRiskResult> ipaplatformrisk;
	private static ObjectMapper mapper = new ObjectMapper();
	public String getIpainfo() {
		try {
			return mapper.writeValueAsString(ipainfo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void setIpainfo(String ipainfo) {
		try {
			this.ipainfo = mapper.readValue(ipainfo, IpaInfo.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getIpapermission() {
		try {
			return mapper.writeValueAsString(ipapermission);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void setIpapermission(String ipapermission) {
		try {
			this.ipapermission = mapper.readValue(ipapermission, String[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getIpaplatformrisk() {
		try {
			return mapper.writeValueAsString(ipaplatformrisk);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public void setIpaplatformrisk(String ipaplatformrisk) {
		try {
			this.ipaplatformrisk = mapper.readValue(ipaplatformrisk, List.class);
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
}
