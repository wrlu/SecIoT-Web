package com.wrlus.seciot.util.exception;

public abstract class PythonException extends RootException {

	private static final long serialVersionUID = -6386840722534713828L;
	private static final String DEFAULT_REASON = "A python error occured.";
	
	public PythonException() {
		super(DEFAULT_REASON);
	}
	
	public PythonException(String reason) {
		super(reason);
	}
	
	public PythonException(Throwable throwable) {
		super(DEFAULT_REASON, throwable);
	}
	
	public PythonException(String reason, Throwable throwable) {
		super(reason, throwable);
	}
	
	public abstract String getPythonError();
}
