package com.wrlus.seciot.fw.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wrlus.seciot.fw.model.FwInfoModel;
import com.wrlus.seciot.fw.model.FwLibraryRiskModel;
import com.wrlus.seciot.fw.model.FwRiskReportModel;
import com.wrlus.seciot.fw.model.FwThirdLibraryModel;
import com.wrlus.seciot.model.PlatformRiskModel;
import com.wrlus.seciot.pysocket.PyClient;
import com.wrlus.seciot.pysocket.model.PySocketRequest;
import com.wrlus.seciot.pysocket.model.PySocketResponse;
import com.wrlus.seciot.pysocket.model.PythonException;
import com.wrlus.seciot.util.Status;

public class FwServiceImpl implements FwService {
	private static Logger log = LogManager.getLogger();

	@Override
	public FwInfoModel getFwInfo(String filename, File fwFile) throws PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("file_name", filename);
		parameters.put("path", fwFile.getAbsolutePath());
		request.setCmd("FwService.get_fw_info");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		try {
			pyClient.connect();
		} catch (IOException e) {
			log.error("Python出现异常，错误代码："+Status.PY_ERROR);
			throw new PythonException("Python出现异常，错误代码："+Status.PY_ERROR);
		}
		PySocketResponse result = pyClient.sendCmdSync(request);
		if (result.getStatus() == Status.SUCCESS) {
			FwInfoModel fwInfo = new FwInfoModel();
			fwInfo.setName(String.valueOf(result.getData().get("fw_name")));
			fwInfo.setPath(String.valueOf(result.getData().get("fw_path")));
			fwInfo.setFilesystem(String.valueOf(result.getData().get("fw_filesystem")));
			return fwInfo;
		} else {
			log.error("Python出现异常，错误代码："+Status.PY_ERROR);
			throw new PythonException("Python出现异常，错误代码："+Status.PY_ERROR);
		}
	}

	@Override
	public File getFwRootDirectory(FwInfoModel fwInfoModel) throws PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fw_info", fwInfoModel);
		request.setCmd("FwService.get_fw_root_directory");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		try {
			pyClient.connect();
		} catch (IOException e) {
			log.error("Python出现异常，错误代码："+Status.PY_ERROR);
			throw new PythonException("Python出现异常，错误代码："+Status.PY_ERROR);
		}
		PySocketResponse result = pyClient.sendCmdSync(request);
		if (result.getStatus() == Status.SUCCESS) {
			File rootDir = new File(String.valueOf(result.getData().get("fw_root_directory")));
			return rootDir;
		} else {
			log.error("Python出现异常，错误代码："+Status.PY_ERROR);
			throw new PythonException("Python出现异常，错误代码："+Status.PY_ERROR);
		}
	}

	@Override
	public FwThirdLibraryModel getFwThirdLibrary(FwInfoModel fwInfo, String libName) throws PythonException {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("base_dir", fwInfo.getRootDir());
		parameters.put("lib_name", libName);
		request.setCmd("FwService.get_fw_info");
		request.setParameters(parameters);
		PyClient pyClient = new PyClient();
		try {
			pyClient.connect();
			PySocketResponse result = pyClient.sendCmdSync(request);
			if (result.getStatus() == Status.SUCCESS) {
				FwThirdLibraryModel fwThirdLibrary = new FwThirdLibraryModel();
				fwThirdLibrary.setAvaliable(Boolean.parseBoolean(String.valueOf(result.getData().get("lib_avaliable"))));
				fwThirdLibrary.setPath(String.valueOf(result.getData().get("lib_path")));
				fwThirdLibrary.setPath(String.valueOf(result.getData().get("lib_version")));
				pyClient.close();
				return fwThirdLibrary;
			} else {
				throw new PythonException("Python出现异常，错误代码："+Status.PY_ERROR);
			}
		} catch (Exception e) {
			log.error("Python出现异常，错误代码："+Status.PY_ERROR);
			throw new PythonException("Python出现异常，错误代码："+Status.PY_ERROR);
		} 
	}

	@Override
	public Map<FwLibraryRiskModel, Boolean> checkFwLibraryRisks(FwInfoModel fwInfo, FwLibraryRiskModel[] fwLibraryRisks) throws PythonException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<PlatformRiskModel, Boolean> checkFwPlatformRisks(FwInfoModel fwInfo, PlatformRiskModel[] platformRisks) throws PythonException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FwRiskReportModel getFwRiskReport() {
		// TODO Auto-generated method stub
		return null;
	}

}
