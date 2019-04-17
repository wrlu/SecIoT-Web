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
import com.wrlus.seciot.pysocket.model.PythonException;
import com.wrlus.seciot.util.Status;


@Service
public class FwServiceImpl implements FwService {
	private static Logger log = LogManager.getLogger();
	
	@Override
	public FwInfo getFwInfo(File fwFile) throws IOException, PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("file_name", fwFile.getName());
		parameters.put("file_path", fwFile.getAbsolutePath());
		request.setCmd("FwService.get_fw_info");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		pyClient.connect();
		PySocketResponse response = pyClient.sendCmdSync(request);
		ObjectMapper mapper = new ObjectMapper();
		log.debug("Response: "+mapper.writeValueAsString(response));
		if (response.getStatus() == Status.SUCCESS) {
			FwInfo fwInfo = mapper.readValue(mapper.writeValueAsString(response.getData()), FwInfo.class);
			return fwInfo;
		} else {
			throw new PythonException("Python出现异常，错误代码："+response.getStatus());
		}
		
	}

	@Override
	public File getFwRootDirectory(FwInfo fwInfoModel) throws IOException, PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fw_info", fwInfoModel);
		request.setCmd("FwService.get_fw_root_directory");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		pyClient.connect();
		PySocketResponse response = pyClient.sendCmdSync(request);
		ObjectMapper mapper = new ObjectMapper();
		log.debug("Response: "+mapper.writeValueAsString(response));
		if (response.getStatus() == Status.SUCCESS) {
			File rootDir = new File(String.valueOf(response.getData().get("fw_root_directory")));
			return rootDir;
		} else {
			throw new PythonException("Python出现异常，错误代码："+response.getStatus());
		}
		
	}

	@Override
	public ThirdLibrary getFwThirdLibrary(FwInfo fwInfo, String libName) throws IOException, PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fw_info", fwInfo);
		parameters.put("lib_name", libName);
		request.setCmd("FwService.get_fw_third_library");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		pyClient.connect();
		PySocketResponse response = pyClient.sendCmdSync(request);
		ObjectMapper mapper = new ObjectMapper();
		log.debug("Response: "+mapper.writeValueAsString(response));
		if (response.getStatus() == Status.SUCCESS) {
			ThirdLibrary fwThirdLibrary = mapper.readValue(mapper.writeValueAsString(response.getData()), ThirdLibrary.class);
			pyClient.close();
			return fwThirdLibrary;
		} else {
			throw new PythonException("Python出现异常，错误代码："+response.getStatus());
		}
	}

	@Override
	public List<PlatformRiskResult> checkFwPlatformRisks(FwInfo fwInfo, PlatformRiskDao[] platformRisks) throws IOException, PythonException {
		List<PlatformRiskResult> results = new ArrayList<>();
		for (PlatformRiskDao platformRisk : platformRisks) {
			PySocketRequest request = new PySocketRequest();
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("fw_info", fwInfo);
			request.setCmd(platformRisk.getPayload());
			request.setParameters(parameters);
			PyClient pyClient = new PyClient();
			pyClient.connect();
			PySocketResponse response = pyClient.sendCmdSync(request);
			ObjectMapper mapper = new ObjectMapper();
			log.debug("Response: "+mapper.writeValueAsString(response));
			if (response.getStatus() == Status.SUCCESS) {
				PlatformRiskResult result = mapper.readValue(mapper.writeValueAsString(response.getData()), PlatformRiskResult.class);
				results.add(result);
				pyClient.close();
			} else {
				throw new PythonException("Python出现异常，错误代码："+response.getStatus());
			}
		}
		return results;
	}

}
