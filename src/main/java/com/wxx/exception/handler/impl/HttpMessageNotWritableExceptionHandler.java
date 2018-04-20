package com.wxx.exception.handler.impl;

import org.springframework.http.converter.HttpMessageNotWritableException;

import com.wxx.exception.handler.AbstractExceptionHandler;

/**
 * 该handler处理 HttpMessageNotWritableException 异常
 * 
 */
public class HttpMessageNotWritableExceptionHandler extends
		AbstractExceptionHandler {
	private final static String CODE = "msgcde";

	public HttpMessageNotWritableExceptionHandler() {
		this(HttpMessageNotWritableException.class);
	}

	public HttpMessageNotWritableExceptionHandler(
			Class<? extends Exception> clazz) {
		super(clazz);
	}

	/**
	 * 设置错误码
	 */
	@Override
	protected String getErrorCode(Exception ex) {
		return HttpMessageNotWritableExceptionHandler.CODE;
	}

	/**
	 * 设置错误信息
	 */
	@Override
	protected String getErrorMsg(Exception ex) {
		return "json串";
	}

}
