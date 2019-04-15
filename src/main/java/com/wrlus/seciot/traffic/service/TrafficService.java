package com.wrlus.seciot.traffic.service;

import java.io.File;
import java.net.InetAddress;
import java.util.Map;

import com.wrlus.seciot.traffic.model.Protocol;
import com.wrlus.seciot.traffic.model.ProtocolRisk;

public interface TrafficService {
	public InetAddress[] getConnectionPairs(File pcapFile, InetAddress ip);
	public Map<Protocol, Boolean> checkProtocolUsage(Protocol[] protocols);
	public Map<ProtocolRisk, Boolean> checkProtocolRisks(ProtocolRisk[] protocolRisks);
}
