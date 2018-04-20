package com.wxx.exception.handler.impl;

import com.wxx.exception.handler.AbstractExceptionHandler;

/**
 * 处理 NullPointerException 异常
 * 
 */
public class NullPointerExceptionHandler extends AbstractExceptionHandler {

	private static final String CODE = "msgcde";

	public NullPointerExceptionHandler() {
		this(NullPointerException.class);
	}

	/**
	 * @param clazz
	 */
	public NullPointerExceptionHandler(Class<? extends Exception> clazz) {
		super(clazz);
	}

	@Override
	protected String getErrorCode(Exception ex) {
		return NullPointerExceptionHandler.CODE;
	}

	@Override
	protected String getErrorMsg(Exception ex) {
		return "json串";
	}

}
