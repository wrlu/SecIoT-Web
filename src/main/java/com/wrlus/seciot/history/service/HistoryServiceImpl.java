package com.wrlus.seciot.history.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wrlus.seciot.history.dao.HistoryMapper;
import com.wrlus.seciot.history.model.AndroidHistoryDao;
import com.wrlus.seciot.history.model.AppleiOSHistoryDao;
import com.wrlus.seciot.history.model.FwHistoryDao;
import com.wrlus.seciot.history.model.HistoryDao;

@Service
public class HistoryServiceImpl implements HistoryService {
	
	@Autowired
	private HistoryMapper dao;

	@Override
	public List<HistoryDao> getHistoryAll() {
		return dao.getHistoryAll();
	}
	
	@Override
	public List<HistoryDao> getHistoryById(String id) {
		return dao.getHistoryById(id);
	}

	@Override
	public List<HistoryDao> getHistoryByType(String type) {
		return dao.getHistoryByType(type);
	}

	@Override
	public List<FwHistoryDao> getFwHistoryById(String id) {
		return dao.getFwHistoryById(id);
	}

	@Override
	public List<AndroidHistoryDao> getAndroidHistoryById(String id) {
		return dao.getAndroidHistoryById(id);
	}

	@Override
	public List<AppleiOSHistoryDao> getAppleiOSHistoryById(String id) {
		return dao.getAppleiOSHistoryById(id);
	}

	@Override
	public int addHistory(HistoryDao history) {
		return dao.addHistory(history);
	}

	@Override
	public int addFwHistory(FwHistoryDao fwHistory) {
		return dao.addFwHistory(fwHistory);
	}

	@Override
	public int addAndroidHistory(AndroidHistoryDao androidHistory) {
		return dao.addAndroidHistory(androidHistory);
	}

	@Override
	public int addAppleiOSHistory(AppleiOSHistoryDao appleiOSHistory) {
		return dao.addAppleiOSHistory(appleiOSHistory);
	}

	@Override
	public int updateHistoryName(String id, String name) {
		return dao.updateHistoryName(id, name);
	}

	@Override
	public int deleteHistory(String id) {
		return dao.deleteHistory(id);
	}

	@Override
	public int deleteFwHistory(String id) {
		return dao.deleteFwHistory(id);
	}

	@Override
	public int deleteAndroidHistory(String id) {
		return dao.deleteAndroidHistory(id);
	}

	@Override
	public int deleteAppleiOSHistory(String id) {
		return dao.deleteAppleiOSHistory(id);
	}

}
