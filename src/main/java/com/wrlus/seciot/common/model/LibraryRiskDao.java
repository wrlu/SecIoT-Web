package com.wrlus.seciot.common.model;

public class LibraryRiskDao {
	private long id;
	private String name;
	private String version;
	private String cve_num;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCve_num() {
		return cve_num;
	}
	public void setCve_num(String cve_num) {
		this.cve_num = cve_num;
	}
	
}
