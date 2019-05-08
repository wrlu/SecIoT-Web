package com.wrlus.seciot.util.exception;

public class FileUploadException extends RootException {

	private static final long serialVersionUID = -6429086061683403202L;
	private static final String DEFAULT_REASON = "Cannot upload file to the server.";
	
	public FileUploadException() {
		super(DEFAULT_REASON);
	}
	
	public FileUploadException(String reason) {
		super(reason);
	}
	
	public FileUploadException(Throwable throwable) {
		super(DEFAULT_REASON, throwable);
	}
	
	public FileUploadException(String reason, Throwable throwable) {
		super(reason, throwable);
	}
	
	@Override
	public ReasonEnum getReason() {
		return ReasonEnum.FILE_UPLOAD;
	}
}
