package com.wrlus.seciot.waf;

public class XSSProtect {
	public static String escapeString(String input) {
		String regex = "[^a-zA-Z0-9\\u4e00-\\u9fa5\\_\\-\\.\\s]";
		return input.replaceAll(regex, "");
	}
	
	public static String escapeUuid(String input) {
		String regex = "[^a-zA-Z0-9\\-]";
		return input.replaceAll(regex, "");
	}
}
