package com.wrlus.seciot.util.exception;

public enum ReasonEnum {
	SUCCESS("成功"),
	UNKNOWN("未知错误"),
	FILE_UPLOAD("上传文件失败"),
	NO_SUCH_PYTHON_METHOD("此检测功能还未实现"),
	NO_SUCH_RISK("未定义该风险"),
	PYTHON_IO("与检测服务器通信时出错"),
	PYTHON_RUNTIME("检测服务器报告了一个错误");
	
	private String reason;
	
	private ReasonEnum(String reason) {
		this.reason = reason;
	}
	
	public String get() {
		return reason;
	}
}
