package com.wxx.exception.handler.impl;

import java.lang.reflect.UndeclaredThrowableException;

import com.wxx.exception.handler.AbstractExceptionHandler;

/**
 * 处理UndeclaredThrowableException异常
 */
public class ThrowableExceptionHandler extends AbstractExceptionHandler {

	public ThrowableExceptionHandler() {
		this(UndeclaredThrowableException.class);
	}

	public ThrowableExceptionHandler(Class<? extends Exception> clazz) {
		super(clazz);
	}

	@Override
	protected String getErrorCode(Exception ex) {
		return "110";
	}

	@Override
	protected String getErrorMsg(Exception ex) {
		return "json串";
	}

}
