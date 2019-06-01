package com.wrlus.seciot.history.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrlus.seciot.fw.model.FwInfo;
import com.wrlus.seciot.library.model.ThirdLibrary;
import com.wrlus.seciot.library.model.ThirdLibraryRiskDao;
import com.wrlus.seciot.platform.model.PlatformRiskResult;

public class FwResultDao {
	private FwInfo fwinfo;
	private List<ThirdLibrary> fwlib;
	private Map<String, List<ThirdLibraryRiskDao>> fwlibrisk;
	private List<PlatformRiskResult> fwplatformrisk;
	private static ObjectMapper mapper = new ObjectMapper();
}
