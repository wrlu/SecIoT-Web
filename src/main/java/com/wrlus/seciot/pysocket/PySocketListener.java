package com.wrlus.seciot.pysocket;

public interface PySocketListener {
	public void onSuccess(PySocketResultModel result);
	public void onError(PySocketResultModel result);
}
