package com.wrlus.seciot.util.exception;

public class PortRunOutException extends RootException {

	private static final long serialVersionUID = 7136684699971113615L;
	private static final String DEFAULT_REASON = "There is no avaliable port on this server (too many clients).";
	
	public PortRunOutException() {
		super(DEFAULT_REASON);
	}
	
	public PortRunOutException(String reason) {
		super(reason);
	}
	
	public PortRunOutException(Throwable throwable) {
		super(DEFAULT_REASON, throwable);
	}
	
	public PortRunOutException(String reason, Throwable throwable) {
		super(reason, throwable);
	}
	
	@Override
	public ReasonEnum getReason() {
		return ReasonEnum.PORT_RUN_OUT;
	}
}
