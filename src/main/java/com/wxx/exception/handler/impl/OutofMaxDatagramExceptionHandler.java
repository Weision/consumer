package com.wxx.exception.handler.impl;

import com.wxx.exception.OutofMaxDatagramException;
import com.wxx.exception.handler.AbstractExceptionHandler;

/**
 * 处理 NullPointerException 异常
 */
public class OutofMaxDatagramExceptionHandler extends AbstractExceptionHandler {

	private static final String CODE = "msgcde";

	public OutofMaxDatagramExceptionHandler() {
		this(OutofMaxDatagramException.class);
	}

	/**
	 * @param clazz
	 */
	public OutofMaxDatagramExceptionHandler(Class<? extends Exception> clazz) {
		super(clazz);
	}

	@Override
	protected String getErrorCode(Exception ex) {
		return OutofMaxDatagramExceptionHandler.CODE;
	}

	@Override
	protected String getErrorMsg(Exception ex) {
		return "json串";
	}

}
