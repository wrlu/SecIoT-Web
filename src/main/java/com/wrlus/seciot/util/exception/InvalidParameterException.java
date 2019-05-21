package com.wrlus.seciot.util.exception;

public class InvalidParameterException extends PythonException {
	
	private static final long serialVersionUID = -3340148969745732673L;
	private static final String DEFAULT_REASON = "Invalid parameter type or value.";
	
	public InvalidParameterException() {
		super(DEFAULT_REASON);
	}
	
	public InvalidParameterException(String reason) {
		super(reason);
	}
	
	public InvalidParameterException(Throwable throwable) {
		super(DEFAULT_REASON, throwable);
	}
	
	public InvalidParameterException (String reason, Throwable throwable) {
		super(reason, throwable);
	}

	@Override
	public String getPythonError() {
		return null;
	}
	
	@Override
	public ReasonEnum getReason() {
		return ReasonEnum.INVALID_PARAM;
	}
	
}
