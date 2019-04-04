package com.wrlus.seciot.fw.service;

import java.io.File;

import org.springframework.stereotype.Service;

import com.wrlus.seciot.fw.model.FwInfoModel;
import com.wrlus.seciot.fw.model.FwRiskReportModel;
import com.wrlus.seciot.fw.model.FwThirdLibraryModel;

@Service
public class FwServiceImpl implements FwService {

	@Override
	public FwInfoModel getFwInfo(File fwFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getFwRootDirectory(FwInfoModel fwInfoModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FwThirdLibraryModel getFwThirdLibrary(String libName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FwRiskReportModel getFwRiskReport() {
		// TODO Auto-generated method stub
		return null;
	}

}
