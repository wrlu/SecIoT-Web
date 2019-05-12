package com.wrlus.seciot.util.exception;

public class NoSuchClientException extends RootException{

	private static final long serialVersionUID = -1558339448296518822L;
	private static final String DEFAULT_REASON = "No such client.";
	
	public NoSuchClientException() {
		super(DEFAULT_REASON);
	}
	
	public NoSuchClientException(String reason) {
		super(reason);
	}
	
	public NoSuchClientException(Throwable throwable) {
		super(DEFAULT_REASON, throwable);
	}
	
	public NoSuchClientException(String reason, Throwable throwable) {
		super(reason, throwable);
	}
	
	@Override
	public ReasonEnum getReason() {
		return ReasonEnum.NO_SUCH_CLINET;
	}

}
