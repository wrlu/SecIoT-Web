package com.wrlus.seciot.pysocket.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PySocketRequest {
	@JsonProperty("cmd")
	private String cmd;
	@JsonProperty("params")
	private Map<String, Object> parameters;
	
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public Map<String, Object> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
}
