package com.wrlus.seciot.scan.service;

import java.net.InetAddress;
import java.util.Map;

import com.wrlus.seciot.scan.model.PortScanReportModel;

public interface PortScanService {
	public int[] getOpenPorts(InetAddress ip, String portRange);
	public Map<Integer, String> getPortServices(InetAddress ip, int[] ports);
	public PortScanReportModel getPortScanReport();
}
