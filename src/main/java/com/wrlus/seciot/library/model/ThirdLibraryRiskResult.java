package com.wrlus.seciot.library.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ThirdLibraryRiskResult {
	@JsonProperty("risk_exists")
	private boolean exists;
	@JsonProperty("risk_name")
	private String name;
	@JsonProperty("risk_description")
	private String description;
	@JsonProperty("risk_level")
	private String level;
	@JsonProperty("risk_platform")
	private String platform;
	@JsonProperty("risk_detail_keys")
	private String[] detailKeys;
	@JsonProperty("risk_details")
	private Map<String, List<String>> details;
	public boolean isExists() {
		return exists;
	}
	public void setExists(boolean exists) {
		this.exists = exists;
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public Map<String, List<String>> getDetails() {
		return details;
	}
	public void setDetails(Map<String, List<String>> details) {
		this.details = details;
	}
}
