package com.wrlus.seciot.util.exception;

public class NoSuchPythonMethodException extends RootException {

	private static final long serialVersionUID = -2789531819000974764L;
	private static final String DEFAULT_REASON = "Python method not found.";
	
	public NoSuchPythonMethodException() {
		super(DEFAULT_REASON);
	}
	
	public NoSuchPythonMethodException(String reason) {
		super(reason);
	}
	
	public NoSuchPythonMethodException(Throwable throwable) {
		super(DEFAULT_REASON, throwable);
	}
	
	public NoSuchPythonMethodException(String reason, Throwable throwable) {
		super(reason, throwable);
	}
	
	@Override
	public ReasonEnum getReason() {
		return ReasonEnum.NO_SUCH_PYTHON_METHOD;
	}
}
