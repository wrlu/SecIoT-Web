package com.wrlus.seciot.util.exception;

public class FridaException extends PythonRuntimeException {

	private static final long serialVersionUID = 3593060171127082582L;
	private static final String DEFAULT_REASON = "Frida module reports an error.";
	
	public FridaException() {
		super(DEFAULT_REASON);
	}
	
	public FridaException(String reason) {
		super(reason);
	}
	
	public FridaException(Throwable throwable) {
		super(DEFAULT_REASON, throwable);
	}
	
	public FridaException(String reason, Throwable throwable) {
		super(reason, throwable);
	}
	
	@Override
	public ReasonEnum getReason() {
		return ReasonEnum.FRIDA_ERROR;
	}

	@Override
	public String getPythonError() {
		return getLocalizedMessage();
	}
	
}
