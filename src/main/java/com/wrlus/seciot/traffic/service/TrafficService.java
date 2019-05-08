package com.wrlus.seciot.traffic.service;

import java.io.File;

import com.wrlus.seciot.traffic.model.ConnectionDetails;
import com.wrlus.seciot.util.exception.PythonException;

@Deprecated
public interface TrafficService {
	public ConnectionDetails getConnectionDetails(File pcapFile, String ip) throws PythonException;
}
