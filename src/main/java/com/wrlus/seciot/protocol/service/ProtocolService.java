package com.wrlus.seciot.protocol.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wrlus.seciot.protocol.model.ProtocolDao;
import com.wrlus.seciot.protocol.model.ProtocolRiskDao;

public interface ProtocolService {
	public List<ProtocolDao> getProtocolAll();
	public List<ProtocolDao> getProtocolByName(String name);
	public List<ProtocolDao> getProtocolByLayer(int layer);
	public List<ProtocolRiskDao> getProtocolRiskAll();
	public List<ProtocolRiskDao> getProtocolRiskById(@Param("id") String id);
	public List<ProtocolRiskDao> getProtocolRiskByProtocol(@Param("protocolName") String protocolName);
}
