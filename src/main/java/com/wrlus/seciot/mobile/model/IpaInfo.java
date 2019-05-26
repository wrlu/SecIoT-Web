package com.wrlus.seciot.mobile.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IpaInfo {
	@JsonProperty("ipa_name")
	private String name;
	@JsonProperty("ipa_size")
	private long size;
	@JsonProperty("ipa_path")
	private String path;
	@JsonProperty("ipa_source_file")
	private String sourceFile;
	@JsonProperty("ipa_info_plist_file")
	private String infoPlistFile;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSourceFile() {
		return sourceFile;
	}
	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	public String getInfoPlistFile() {
		return infoPlistFile;
	}
	public void setInfoPlistFile(String infoPlistFile) {
		this.infoPlistFile = infoPlistFile;
	}
	
}
