package com.wrlus.seciot.fw.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrlus.seciot.fw.model.FwInfo;
import com.wrlus.seciot.library.model.ThirdLibrary;
import com.wrlus.seciot.platform.model.PlatformRiskDao;
import com.wrlus.seciot.platform.model.PlatformRiskResult;
import com.wrlus.seciot.pysocket.PyClient;
import com.wrlus.seciot.pysocket.model.PySocketRequest;
import com.wrlus.seciot.pysocket.model.PySocketResponse;
import com.wrlus.seciot.util.exception.PythonException;
import com.wrlus.seciot.util.exception.PythonIOException;
import com.wrlus.seciot.util.exception.PythonRuntimeException;


@Service
public class FwServiceImpl implements FwService {
	private static Logger log = LogManager.getLogger();
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public FwInfo getFwInfo(File fwFile) throws PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("file_name", fwFile.getName());
		parameters.put("file_path", fwFile.getAbsolutePath());
		request.setCmd("FwService.get_fw_info");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		pyClient.connect();
		PySocketResponse response = pyClient.sendCmdSync(request);
		log.debug(response.toString());
		try {
			pyClient.close();
		} catch (IOException e) {
			throw new PythonIOException("An error occured when parsing response from python server.", e);
		}
		if (response.getStatus() == 0) {
			try {
				FwInfo fwInfo = mapper.readValue(mapper.writeValueAsString(response.getData()), FwInfo.class);
				return fwInfo;
			} catch (Exception e) {
				throw new PythonIOException("An error occured when parsing response from python server.", e);
			}
		} else {
			throw new PythonRuntimeException();
		}
	}

	@Override
	public File getFwRootDirectory(FwInfo fwInfoModel) throws PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fw_info", fwInfoModel);
		request.setCmd("FwService.get_fw_root_directory");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		pyClient.connect();
		PySocketResponse response = pyClient.sendCmdSync(request);
		log.debug(response.toString());
		try {
			pyClient.close();
		} catch (IOException e) {
			throw new PythonIOException("An error occured when parsing response from python server.", e);
		}
		if (response.getStatus() == 0) {
			File rootDir = new File(String.valueOf(response.getData().get("fw_root_directory")));
			return rootDir;
		} else {
			throw new PythonRuntimeException();
		}
	}

	@Override
	public ThirdLibrary getFwThirdLibrary(FwInfo fwInfo, String libName) throws PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fw_info", fwInfo);
		parameters.put("lib_name", libName);
		request.setCmd("FwService.get_fw_third_library");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		pyClient.connect();
		PySocketResponse response = pyClient.sendCmdSync(request);
		try {
			pyClient.close();
		} catch (IOException e) {
			throw new PythonIOException("An error occured when parsing response from python server.", e);
		}
		log.debug(response.toString());
		if (response.getStatus() == 0) {
			try {
				ThirdLibrary fwThirdLibrary = mapper.readValue(mapper.writeValueAsString(response.getData()), ThirdLibrary.class);
				return fwThirdLibrary;
			} catch (Exception e) {
				throw new PythonIOException("An error occured when parsing response from python server.", e);
			}
		} else {
			throw new PythonRuntimeException();
		}
	}

	@Override
	public List<PlatformRiskResult> checkFwPlatformRisks(FwInfo fwInfo, PlatformRiskDao[] platformRisks) throws PythonException {
		List<PlatformRiskResult> results = new ArrayList<>();
		int successCount = 0;
		for (PlatformRiskDao platformRisk : platformRisks) {
			PySocketRequest request = new PySocketRequest();
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("fw_info", fwInfo);
			request.setCmd(platformRisk.getPayload());
			request.setParameters(parameters);
			PyClient pyClient = new PyClient();
			pyClient.connect();
			PySocketResponse response = pyClient.sendCmdSync(request);
			try {
				pyClient.close();
			} catch (IOException e) {
				throw new PythonIOException("An error occured when parsing response from python server.", e);
			}
			log.debug(response.toString());
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
