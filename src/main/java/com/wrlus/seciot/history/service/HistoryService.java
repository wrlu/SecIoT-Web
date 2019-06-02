package com.wrlus.seciot.history.service;

import java.util.List;

import com.wrlus.seciot.history.model.AndroidHistoryDao;
import com.wrlus.seciot.history.model.AppleiOSHistoryDao;
import com.wrlus.seciot.history.model.FwHistoryDao;
import com.wrlus.seciot.history.model.HistoryDao;

public interface HistoryService {
	public List<HistoryDao> getHistoryAll();
	public List<HistoryDao> getHistoryById(String id);
	public List<HistoryDao> getHistoryByType(String type);
	public List<FwHistoryDao> getFwHistoryById(String id);
	public List<AndroidHistoryDao> getAndroidHistoryById(String id);
	public List<AppleiOSHistoryDao> getAppleiOSHistoryById(String id);
	public int addHistory(HistoryDao history);
	public int addFwHistory(FwHistoryDao fwHistory);
	public int addAndroidHistory(AndroidHistoryDao androidHistory);
	public int addAppleiOSHistory(AppleiOSHistoryDao appleiOSHistory);
	public int updateHistoryName(String id, String name);
	public int deleteHistory(String id);
	public int deleteFwHistory(String id);
	public int deleteAndroidHistory(String id);
	public int deleteAppleiOSHistory(String id);
}
