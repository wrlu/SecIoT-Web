package com.wrlus.seciot.util.exception;

public class PythonRuntimeException extends PythonException {
	
	private static final long serialVersionUID = -3980967766530821832L;
	private static final String DEFAULT_REASON = "An error occured when python script is running.";
	
	public PythonRuntimeException() {
		super(DEFAULT_REASON);
	}
	
	public PythonRuntimeException(String reason) {
		super(reason);
	}
	
	public PythonRuntimeException(Throwable throwable) {
		super(DEFAULT_REASON, throwable);
	}
	
	public PythonRuntimeException (String reason, Throwable throwable) {
		super(reason, throwable);
	}

	@Override
	public String getPythonError() {
		return null;
	}
	
	@Override
	public ReasonEnum getReason() {
		return ReasonEnum.PYTHON_RUNTIME;
	}
	
}
