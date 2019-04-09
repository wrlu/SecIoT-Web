package com.wrlus.seciot.common.model;

public class ThirdLibraryDao {
	private int id;
	private String name;
	private String description;
	private String latest_version;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLatest_version() {
		return latest_version;
	}
	public void setLatest_version(String latest_version) {
		this.latest_version = latest_version;
	}
	
}
