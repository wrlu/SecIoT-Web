package com.wrlus.seciot.device.model;

public class DeviceDao {
	private String clientid;
	private String devicename;
	private String version;
	private int apilevel;
	private String agentver;
	private int online;
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getApilevel() {
		return apilevel;
	}
	public void setApilevel(int apilevel) {
		this.apilevel = apilevel;
	}
	public String getAgentver() {
		return agentver;
	}
	public void setAgentver(String agentver) {
		this.agentver = agentver;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	
}
