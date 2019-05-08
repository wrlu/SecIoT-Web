package com.wrlus.seciot.util.exception;

public class RootException extends Exception {
	private static final long serialVersionUID = -4956900992196314341L;
	private static final String DEFAULT_REASON = "Unknown reason.";
	
	public RootException() {
		super(DEFAULT_REASON);
	}
	
	public RootException(String reason) {
		super(reason);
	}
	
	public RootException(Throwable throwable) {
		super(DEFAULT_REASON, throwable);
	}
	
	public RootException(String reason, Throwable throwable) {
		super(reason, throwable);
	}
	
	public ReasonEnum getReason() {
		return ReasonEnum.UNKNOWN;
	}
	
}
