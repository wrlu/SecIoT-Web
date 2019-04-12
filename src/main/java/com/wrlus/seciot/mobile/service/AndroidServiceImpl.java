package com.wrlus.seciot.mobile.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrlus.seciot.common.model.PlatformRiskDao;
import com.wrlus.seciot.mobile.model.ApkInfoModel;
import com.wrlus.seciot.pysocket.PyClient;
import com.wrlus.seciot.pysocket.model.PySocketRequest;
import com.wrlus.seciot.pysocket.model.PySocketResponse;
import com.wrlus.seciot.pysocket.model.PythonException;
import com.wrlus.seciot.util.Status;

public class AndroidServiceImpl implements AndroidService {

	@Override
	public ApkInfoModel getApkInfo(File apkFile) throws IOException, PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("file_name", apkFile.getName());
		parameters.put("file_path", apkFile.getAbsolutePath());
		request.setCmd("AndroidService.get_apk_info");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		pyClient.connect();
		PySocketResponse result = pyClient.sendCmdSync(request);
		if (result.getStatus() == Status.SUCCESS) {
			ObjectMapper mapper = new ObjectMapper();
			ApkInfoModel apkInfo = mapper.readValue(mapper.writeValueAsString(result.getData()), ApkInfoModel.class);
			return apkInfo;
		} else {
			throw new PythonException("Python出现异常，错误代码："+result.getStatus());
		}
	}

	@Override
	public String[] getAndroidPermissions(File manifestFile) throws IOException, PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("file_name", manifestFile.getName());
		parameters.put("file_path", manifestFile.getAbsolutePath());
		request.setCmd("AndroidService.permission");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		pyClient.connect();
		PySocketResponse result = pyClient.sendCmdSync(request);
		if (result.getStatus() == Status.SUCCESS) {
			ObjectMapper mapper = new ObjectMapper();
			String[] permissions = mapper.readValue(mapper.writeValueAsString(result.getData()), String[].class);
			return permissions;
		} else {
			throw new PythonException("Python出现异常，错误代码："+result.getStatus());
		}
	}

	@Override
	public Map<PlatformRiskDao, Boolean> checkApkPlatformRisks(PlatformRiskDao[] platformRisks) {
		// TODO Auto-generated method stub
		return null;
	}


}
