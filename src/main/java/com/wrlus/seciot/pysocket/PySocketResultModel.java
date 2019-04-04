package com.wrlus.seciot.pysocket;

import java.io.File;

public class PySocketResultModel {
	private int status;
	private String reason;
	private File logFile;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public File getLogFile() {
		return logFile;
	}
	public void setLogFile(File logFile) {
		this.logFile = logFile;
	}
}
