package com.wrlus.seciot.history.service;

import java.util.List;

import com.wrlus.seciot.history.model.FwHistoryDao;
import com.wrlus.seciot.history.model.HistoryDao;

public interface HistoryService {
	public List<HistoryDao> getHistoryAll();
	public List<HistoryDao> getHistoryById(String id);
	public List<HistoryDao> getHistoryByType(String type);
	public List<FwHistoryDao> getFwHistoryById(String id);
	public int addHistory(HistoryDao history);
	public int addFwHistory(FwHistoryDao fwHistory);
	public int updateHistoryName(String id, String name);
	public int deleteHistory(String id);
	public int deleteFwHistory(String id);
}
