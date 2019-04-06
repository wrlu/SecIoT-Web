package com.wrlus.seciot.pysocket;

import java.util.Map;

public class PySocketResponse {
	
	private int status;
	private String reason;
	private Map<String, Object> data;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
