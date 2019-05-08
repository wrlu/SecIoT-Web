package com.wrlus.seciot.util.exception;

public class NoSuchRiskException extends RootException {

	private static final long serialVersionUID = 5528801655820018054L;
	private static final String DEFAULT_REASON = "Risk not found.";
	
	public NoSuchRiskException() {
		super(DEFAULT_REASON);
	}
	
	public NoSuchRiskException(String reason) {
		super(reason);
	}
	
	public NoSuchRiskException(Throwable throwable) {
		super(DEFAULT_REASON, throwable);
	}
	
	public NoSuchRiskException(String reason, Throwable throwable) {
		super(reason, throwable);
	}
	
	@Override
	public ReasonEnum getReason() {
		return ReasonEnum.NO_SUCH_RISK;
	}
}
