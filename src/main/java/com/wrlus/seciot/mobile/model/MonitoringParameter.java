package com.wrlus.seciot.mobile.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonitoringParameter {
	private String clientId;
	private int port;
	private String process;
	@JsonProperty("monitoring_api")
	private boolean isMonitoringApi;
	@JsonProperty("monitoring_ip")
	private boolean isMonitoringIp;
	@JsonProperty("monitoring_traffic")
	private boolean isMonitoringTraffic;
	@JsonProperty("monitoring_fileio")
	private boolean isMonitoringFileIO;
	@JsonProperty("monitoring_dbio")
	private boolean isMonitoringDatabaseIO;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public boolean isMonitoringApi() {
		return isMonitoringApi;
	}
	public void setMonitoringApi(boolean isMonitoringApi) {
		this.isMonitoringApi = isMonitoringApi;
	}
	public boolean isMonitoringIp() {
		return isMonitoringIp;
	}
	public void setMonitoringIp(boolean isMonitoringIp) {
		this.isMonitoringIp = isMonitoringIp;
	}
	public boolean isMonitoringTraffic() {
		return isMonitoringTraffic;
	}
	public void setMonitoringTraffic(boolean isMonitoringTraffic) {
		this.isMonitoringTraffic = isMonitoringTraffic;
	}
	public boolean isMonitoringFileIO() {
		return isMonitoringFileIO;
	}
	public void setMonitoringFileIO(boolean isMonitoringFileIO) {
		this.isMonitoringFileIO = isMonitoringFileIO;
	}
	public boolean isMonitoringDatabaseIO() {
		return isMonitoringDatabaseIO;
	}
	public void setMonitoringDatabaseIO(boolean isMonitoringDatabaseIO) {
		this.isMonitoringDatabaseIO = isMonitoringDatabaseIO;
	}
}
