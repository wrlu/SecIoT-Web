package com.wrlus.seciot.device.model;

import com.wrlus.seciot.waf.XSSProtect;

public class DeviceDao {
	private String clientid;
	private String devicename;
	private String version;
	private int apilevel;
	private String agentver;
	private int port;
	private int online;
	private int busy;
	
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = XSSProtect.escapeUuid(clientid);
	}
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = XSSProtect.escapeString(devicename);
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
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public int getBusy() {
		return busy;
	}
	public void setBusy(int busy) {
		this.busy = busy;
	}
	
}
