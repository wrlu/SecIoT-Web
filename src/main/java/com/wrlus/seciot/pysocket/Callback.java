package com.wrlus.seciot.pysocket;

import com.wrlus.seciot.pysocket.model.PySocketResponse;

public interface Callback {
	public void onSuccess(PySocketResponse result);
	public void onError(PySocketResponse result);
}
