package com.wrlus.iotsecp.common.entity;

import java.io.IOException;

public class Config {
	/**
	 * 进行全局初始化
	 * @throws IOException
	 */
	public void init() throws IOException {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		System.out.println(path);
	}
}
