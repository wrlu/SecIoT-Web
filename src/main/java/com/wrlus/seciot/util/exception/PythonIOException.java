package com.wrlus.seciot.util.exception;

public class PythonIOException extends PythonException {
	
	private static final long serialVersionUID = -3955759282002405579L;
	private static final String DEFAULT_REASON = "An error occured when execute python script.";
	
	public PythonIOException() {
		super(DEFAULT_REASON);
	}
	
	public PythonIOException(String reason) {
		super(reason);
	}
	
	public PythonIOException(Throwable throwable) {
		super(DEFAULT_REASON, throwable);
	}
	
	public PythonIOException(String reason, Throwable throwable) {
		super(reason, throwable);
	}
	
	@Override
	public ReasonEnum getReason() {
		return ReasonEnum.PYTHON_IO;
	}

	@Override
	public String getPythonError() {
		return getLocalizedMessage();
	}
}
