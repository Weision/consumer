package com.wxx.exception;

public class JSonFormatException extends Exception {
	private static final long serialVersionUID = -6875382863019585533L;
	private String msg;
	private String msgCde;
	@SuppressWarnings("unused")
	private Throwable t;

	public JSonFormatException() {
		this.msg = "JSON报文格式错误";
	}

	public JSonFormatException(String msg) {
		this.msg = msg;
	}

	public JSonFormatException(String msg, Throwable t) {
		this.msg = msg;
		this.t = t;
	}

	public String getJsonError() {

		return this.msg;
	}

	public String getJsonErrorCode() {
		return msgCde;
	}
}
