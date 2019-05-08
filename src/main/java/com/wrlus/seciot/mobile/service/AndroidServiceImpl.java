package com.wrlus.seciot.mobile.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrlus.seciot.mobile.model.ApkInfo;
import com.wrlus.seciot.platform.model.PlatformRiskDao;
import com.wrlus.seciot.platform.model.PlatformRiskResult;
import com.wrlus.seciot.pysocket.PyClient;
import com.wrlus.seciot.pysocket.model.PySocketRequest;
import com.wrlus.seciot.pysocket.model.PySocketResponse;
import com.wrlus.seciot.util.exception.PythonException;
import com.wrlus.seciot.util.exception.PythonIOException;
import com.wrlus.seciot.util.exception.PythonRuntimeException;

@Service
public class AndroidServiceImpl implements AndroidService {
	private static Logger log = LogManager.getLogger();
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public ApkInfo getApkInfo(File apkFile) throws PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("file_name", apkFile.getName());
		parameters.put("file_path", apkFile.getAbsolutePath());
		request.setCmd("AndroidService.get_apk_info");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		pyClient.connect();
		PySocketResponse result = pyClient.sendCmdSync(request);
		if (result.getStatus() == 0) {
			try {
				ApkInfo apkInfo = mapper.readValue(mapper.writeValueAsString(result.getData()), ApkInfo.class);
				return apkInfo;
			} catch (Exception e) {
				throw new PythonIOException("An error occured when parsing response from python server.", e);
			}
		} else {
			throw new PythonRuntimeException();
		}
	}

	@Override
	public String[] getAndroidPermissions(ApkInfo apkInfo) throws PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("apk_info", apkInfo);
		request.setCmd("AndroidService.permission");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		pyClient.connect();
		PySocketResponse result = pyClient.sendCmdSync(request);
		if (result.getStatus() == 0) {
			try {
				String[] permissions = mapper.readValue(mapper.writeValueAsString(result.getData().get("permission")), String[].class);
				return permissions;
			} catch (Exception e) {
				throw new PythonIOException("An error occured when parsing response from python server.", e);
			}
		} else {
			throw new PythonRuntimeException();
		}
	}

	@Override
	public List<PlatformRiskResult> checkApkPlatformRisks(ApkInfo apkInfo, PlatformRiskDao[] platformRisks) throws PythonException {
		List<PlatformRiskResult> results = new ArrayList<>();
		int successCount = 0;
		for (PlatformRiskDao platformRisk : platformRisks) {
			PySocketRequest request = new PySocketRequest();
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("apk_info", apkInfo);
			request.setCmd(platformRisk.getPayload());
			request.setParameters(parameters);
			PyClient pyClient = new PyClient();
			pyClient.connect();
			PySocketResponse response = pyClient.sendCmdSync(request);
			if (response.getStatus() == 0) {
				try {
					PlatformRiskResult result = mapper.readValue(mapper.writeValueAsString(response.getData()), PlatformRiskResult.class);
					results.add(result);
					++successCount;
				} catch (Exception e) {
					log.error(e.getClass().getName() + ": " + e.getLocalizedMessage());
					if (log.isDebugEnabled()) {
						e.printStackTrace();
					}
				}
			} else {
				log.error("Failed to check platform risk item: "+platformRisk.getName());
			}
		}
		if (successCount != platformRisks.length) {
			log.warn("Some platform risk checking script runs failed.");
		}
		return results;
	}
}
