package com.wrlus.seciot.pysocket;

import java.util.Map;

public class PySocketRequest {
	
	private String cmd;
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
