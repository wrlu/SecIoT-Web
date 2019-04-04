package com.wrlus.seciot.traffic.service;

import java.io.File;
import java.net.InetAddress;
import java.util.Map;

import com.wrlus.seciot.traffic.model.ProtocolModel;
import com.wrlus.seciot.traffic.model.ProtocolRiskModel;
import com.wrlus.seciot.traffic.model.TrafficRiskReportModel;

public interface ProtocolService {
	public InetAddress[] getConnectionPairs(File pcapFile, InetAddress ip);
	public Map<ProtocolModel, Boolean> checkProtocolUsage(ProtocolModel[] protocols);
	public Map<ProtocolRiskModel, Boolean> checkProtocolRisks(ProtocolRiskModel[] protocolRisks);
	public TrafficRiskReportModel geTrafficRiskReport();
}
