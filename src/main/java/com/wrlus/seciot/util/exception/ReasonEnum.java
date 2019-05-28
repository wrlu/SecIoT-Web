package com.wrlus.seciot.util.exception;

public enum ReasonEnum {
	SUCCESS("成功"),
	UNKNOWN("未知错误"),
	FILE_UPLOAD("上传文件失败"),
	NO_SUCH_PYTHON_METHOD("没有那个检测功能"),
	NO_SUCH_RISK("没有那个风险"),
	PYTHON_IO("与检测服务器通信时出错"),
	PYTHON_RUNTIME("检测服务器报告了一个错误"),
	PORT_RUN_OUT("已达到最大客户端连接数"),
	CLIENT_ALREADY_EXISTS("客户端已注册过映射端口"),
	NO_SUCH_CLINET("没有那个客户端"),
	INVALID_PARAM("参数错误"),
	FRIDA_ERROR("远程调试服务器报告了一个错误");
	
	private String reason;
	
	private ReasonEnum(String reason) {
		this.reason = reason;
	}
	
	public String get() {
		return reason;
	}
}
