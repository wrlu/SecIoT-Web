package com.wrlus.seciot.traffic.service;

import java.io.File;
import java.net.InetAddress;
import java.util.List;

import com.wrlus.seciot.traffic.model.Protocol;

public interface TrafficService {
	public InetAddress[] getConnectionPairs(File pcapFile, InetAddress ip);
	public List<Boolean> checkProtocolUsage(File pcapFile, Protocol[] protocols);
}
