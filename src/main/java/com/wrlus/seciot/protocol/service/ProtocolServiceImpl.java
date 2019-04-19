package com.wrlus.seciot.protocol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wrlus.seciot.protocol.dao.ProtocolMapper;
import com.wrlus.seciot.protocol.model.ProtocolDao;
import com.wrlus.seciot.protocol.model.ProtocolRiskDao;

@Service
public class ProtocolServiceImpl implements ProtocolService {
	
	@Autowired
	private ProtocolMapper dao;

	@Override
	public List<ProtocolDao> getProtocolAll() {
		return dao.getProtocolAll();
	}

	@Override
	public List<ProtocolDao> getProtocolByName(String name) {
		return dao.getProtocolByName(name);
	}

	@Override
	public List<ProtocolDao> getProtocolByLayer(int layer) {
		return dao.getProtocolByLayer(layer);
	}

	@Override
	public List<ProtocolRiskDao> getProtocolRiskAll() {
		return dao.getProtocolRiskAll();
	}

	@Override
	public List<ProtocolRiskDao> getProtocolRiskById(String id) {
		return dao.getProtocolRiskById(id);
	}

	@Override
	public List<ProtocolRiskDao> getProtocolRiskByProtocol(String protocolName) {
		return dao.getProtocolRiskByProtocol(protocolName);
	}

}
