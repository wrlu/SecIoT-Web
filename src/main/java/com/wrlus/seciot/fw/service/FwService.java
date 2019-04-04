package com.wrlus.seciot.fw.service;

import java.io.File;

import com.wrlus.seciot.fw.model.FwInfoModel;

public interface FwService {
	public FwInfoModel getFwInfo(File fwFile);
	public File getFwRootDirectory(FwInfoModel fwInfoModel);
}
