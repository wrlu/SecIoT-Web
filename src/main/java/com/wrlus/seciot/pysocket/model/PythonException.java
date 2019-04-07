package com.wrlus.seciot.pysocket.model;

import java.io.IOException;

public class PythonException extends IOException {
	
	private static final long serialVersionUID = -3980967766530821832L;
	
	public PythonException() {
		super();
	}
	
	public PythonException(String string) {
		super(string);
	}
	
	public PythonException(Throwable throwable) {
		super(throwable);
	}
	
	public PythonException (String string, Throwable throwable) {
		super(string, throwable);
	}
	
}
