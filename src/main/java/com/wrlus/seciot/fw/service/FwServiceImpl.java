package com.wrlus.seciot.fw.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.wrlus.seciot.fw.model.FwInfoModel;
import com.wrlus.seciot.fw.model.FwLibraryRiskModel;
import com.wrlus.seciot.fw.model.FwRiskReportModel;
import com.wrlus.seciot.fw.model.FwThirdLibraryModel;
import com.wrlus.seciot.model.PlatformRiskModel;
import com.wrlus.seciot.pysocket.PySocket;
import com.wrlus.seciot.pysocket.PySocketRequest;
import com.wrlus.seciot.pysocket.PySocketResponse;
import com.wrlus.seciot.util.Status;

public class FwServiceImpl implements FwService {

	@Override
	public FwInfoModel getFwInfo(String filename, File fwFile) {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("file_name", filename);
		parameters.put("path", fwFile.getAbsolutePath());
		request.setCmd("FwService.get_fw_info");
		request.setParameters(parameters);
		PySocket pySocket = new PySocket();
		PySocketResponse result = pySocket.sendCmdSync(request);
		if (result.getStatus() == Status.SUCCESS) {
			FwInfoModel fwInfo = new FwInfoModel();
			fwInfo.setName(String.valueOf(result.getData().get("fw_name")));
			fwInfo.setPath(String.valueOf(result.getData().get("fw_path")));
			fwInfo.setFilesystem(String.valueOf(result.getData().get("fw_filesystem")));
			return fwInfo;
		}
		return null;
	}

	@Override
	public File getFwRootDirectory(FwInfoModel fwInfoModel) {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fw_info", fwInfoModel);
		request.setCmd("FwService.get_fw_root_directory");
		request.setParameters(parameters);
		PySocket pySocket = new PySocket();
		PySocketResponse result = pySocket.sendCmdSync(request);
		if (result.getStatus() == Status.SUCCESS) {
			File rootDir = new File(String.valueOf(result.getData().get("fw_root_directory")));
			return rootDir;
		}
		return null;
	}

	@Override
	public FwThirdLibraryModel getFwThirdLibrary(FwInfoModel fwInfo, String libName) {
		PySocketRequest request = new PySocketRequest();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("base_dir", fwInfo.getRootDir());
		parameters.put("lib_name", libName);
		request.setCmd("FwService.get_fw_info");
		request.setParameters(parameters);
		PySocket pySocket = new PySocket();
		PySocketResponse result = pySocket.sendCmdSync(request);
		if (result.getStatus() == Status.SUCCESS) {
			FwThirdLibraryModel fwThirdLibrary = new FwThirdLibraryModel();
			fwThirdLibrary.setAvaliable(Boolean.parseBoolean(String.valueOf(result.getData().get("lib_avaliable"))));
			fwThirdLibrary.setPath(String.valueOf(result.getData().get("lib_path")));
			fwThirdLibrary.setPath(String.valueOf(result.getData().get("lib_version")));
			return fwThirdLibrary;
		}
		return null;
	}

	@Override
	public Map<FwLibraryRiskModel, Boolean> checkFwLibraryRisks(FwInfoModel fwInfo, FwLibraryRiskModel[] fwLibraryRisks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<PlatformRiskModel, Boolean> checkFwPlatformRisks(FwInfoModel fwInfo, PlatformRiskModel[] platformRisks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FwRiskReportModel getFwRiskReport() {
		// TODO Auto-generated method stub
		return null;
	}

}
