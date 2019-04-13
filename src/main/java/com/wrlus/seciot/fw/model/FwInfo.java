package com.wrlus.seciot.fw.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FwInfo {
	
	@JsonProperty("fw_name")
	private String name;
	@JsonProperty("fw_path")
	private String path;
	@JsonProperty("fw_size")
	private long size;
	@JsonProperty("fw_filesystem")
	private String filesystem;
	@JsonProperty("fw_root_directory")
	private String rootDir;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getFilesystem() {
		return filesystem;
	}
	public void setFilesystem(String filesystem) {
		this.filesystem = filesystem;
	}
	public String getRootDir() {
		return rootDir;
	}
	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}
	
}
