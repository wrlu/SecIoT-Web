package com.wrlus.seciot.traffic.service;

import java.io.File;
import java.io.IOException;

import com.wrlus.seciot.pysocket.model.PythonException;
import com.wrlus.seciot.traffic.model.ConnectionDetails;

public interface TrafficService {
	public ConnectionDetails getConnectionDetails(File pcapFile, String ip) throws IOException, PythonException;
}
