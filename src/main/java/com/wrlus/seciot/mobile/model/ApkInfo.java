package com.wrlus.seciot.mobile.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApkInfo {
	
	@JsonProperty("apk_name")
	private String name;
	@JsonProperty("apk_size")
	private long size;
	@JsonProperty("apk_path")
	private String path;
	@JsonProperty("apk_sources_path")
	private String sourcesPath;
	@JsonProperty("apk_resources_path")
	private String resourcesPath;
	@JsonProperty("apk_manifest_file")
	private String manifestFile;
	@JsonProperty("apk_ndk_lib_path")
	private String ndkLibPath;
	@JsonProperty("apk_ndk_lib_support_abis")
	private List<String> ndkLibSupportAbis;
	
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
	public String getSourcesPath() {
		return sourcesPath;
	}
	public void setSourcesPath(String sourcesPath) {
		this.sourcesPath = sourcesPath;
	}
	public String getResourcesPath() {
		return resourcesPath;
	}
	public void setResourcesPath(String resourcesPath) {
		this.resourcesPath = resourcesPath;
	}
	public String getManifestFile() {
		return manifestFile;
	}
	public void setManifestFile(String manifestFile) {
		this.manifestFile = manifestFile;
	}
	public String getNdkLibPath() {
		return ndkLibPath;
	}
	public void setNdkLibPath(String ndkLibPath) {
		this.ndkLibPath = ndkLibPath;
	}
	public List<String> getNdkLibSupportAbis() {
		return ndkLibSupportAbis;
	}
	public void setNdkLibSupportAbis(List<String> ndkLibSupportAbis) {
		this.ndkLibSupportAbis = ndkLibSupportAbis;
	}
	
}
