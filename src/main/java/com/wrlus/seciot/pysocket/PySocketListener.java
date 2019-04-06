package com.wrlus.seciot.pysocket;

public interface PySocketListener {
	public void onSuccess(PySocketResponse result);
	public void onError(PySocketResponse result);
}
