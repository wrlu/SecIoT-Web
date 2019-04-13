package com.wrlus.seciot.library.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ThirdLibrary {
	
	@JsonProperty("lib_name")
	private String name;
	@JsonProperty("lib_avaliable")
	private boolean avaliable;
	@JsonProperty("lib_path")
	private String path;
	@JsonProperty("lib_version")
	private String version;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAvaliable() {
		return avaliable;
	}
	public void setAvaliable(boolean avaliable) {
		this.avaliable = avaliable;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
