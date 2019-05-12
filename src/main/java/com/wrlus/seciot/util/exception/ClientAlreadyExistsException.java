package com.wrlus.seciot.util.exception;

public class ClientAlreadyExistsException extends RootException{

	private static final long serialVersionUID = -1558339448296518822L;
	private static final String DEFAULT_REASON = "Client already exists.";
	
	public ClientAlreadyExistsException() {
		super(DEFAULT_REASON);
	}
	
	public ClientAlreadyExistsException(String reason) {
		super(reason);
	}
	
	public ClientAlreadyExistsException(Throwable throwable) {
		super(DEFAULT_REASON, throwable);
	}
	
	public ClientAlreadyExistsException(String reason, Throwable throwable) {
		super(reason, throwable);
	}
	
	@Override
	public ReasonEnum getReason() {
		return ReasonEnum.CLIENT_ALREADY_EXISTS;
	}

}
