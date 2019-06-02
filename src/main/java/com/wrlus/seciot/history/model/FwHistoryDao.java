package com.wrlus.seciot.history.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrlus.seciot.fw.model.FwInfo;
import com.wrlus.seciot.library.model.ThirdLibrary;
import com.wrlus.seciot.library.model.ThirdLibraryRiskDao;
import com.wrlus.seciot.platform.model.PlatformRiskResult;

public class FwHistoryDao {
	private String id;
	private FwInfo fwinfo;
	private List<ThirdLibrary> fwlib;
	private Map<String, List<ThirdLibraryRiskDao>> fwlibrisk;
	private List<PlatformRiskResult> fwplatformrisk;
	private static ObjectMapper mapper = new ObjectMapper();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFwinfo() {
		try {
			return mapper.writeValueAsString(fwinfo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void setFwinfo(String fwinfo) {
		try {
			this.fwinfo = mapper.readValue(fwinfo, FwInfo.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getFwlib() {
		try {
			return mapper.writeValueAsString(fwlib);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public void setFwlib(String fwlib) {
		try {
			this.fwlib = mapper.readValue(fwlib, List.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getFwlibrisk() {
		try {
			return mapper.writeValueAsString(fwlibrisk);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public void setFwlibrisk(String fwlibrisk) {
		try {
			this.fwlibrisk = mapper.readValue(fwlibrisk, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getFwplatformrisk() {
		try {
			return mapper.writeValueAsString(fwplatformrisk);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public void setFwplatformrisk(String fwplatformrisk) {
		try {
			this.fwplatformrisk = mapper.readValue(fwplatformrisk, List.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public FwInfo getFwinfoRaw() {
		return fwinfo;
	}
	public List<ThirdLibrary> getFwlibRaw() {
		return fwlib;
	}
	public Map<String, List<ThirdLibraryRiskDao>> getFwlibriskRaw() {
		return fwlibrisk;
	}
	public List<PlatformRiskResult> getFwplatformriskRaw() {
		return fwplatformrisk;
	}
	public void setFwinfo(FwInfo fwinfo) {
		this.fwinfo = fwinfo;
	}
	public void setFwlib(List<ThirdLibrary> fwlib) {
		this.fwlib = fwlib;
	}
	public void setFwlibrisk(Map<String, List<ThirdLibraryRiskDao>> fwlibrisk) {
		this.fwlibrisk = fwlibrisk;
	}
	public void setFwplatformrisk(List<PlatformRiskResult> fwplatformrisk) {
		this.fwplatformrisk = fwplatformrisk;
	}
	
}
