package com.wrlus.seciot.common.model;

public class CVEDao {
	private String cve_num;
	private String level;
	private String description;
	private String platform;
	private String statment;
	private String payload;
	public String getCve_num() {
		return cve_num;
	}
	public void setCve_num(String cve_num) {
		this.cve_num = cve_num;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getStatment() {
		return statment;
	}
	public void setStatment(String statment) {
		this.statment = statment;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
}
