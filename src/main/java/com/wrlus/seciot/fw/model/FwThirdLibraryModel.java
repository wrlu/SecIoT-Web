package com.wrlus.seciot.fw.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FwThirdLibraryModel {
	
	@JsonProperty("lib_avaliable")
	private boolean isAvaliable;
	@JsonProperty("lib_path")
	private String path;
	@JsonProperty("lib_version")
	private String version;
	
	public boolean isAvaliable() {
		return isAvaliable;
	}
	public void setAvaliable(boolean isAvaliable) {
		this.isAvaliable = isAvaliable;
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
