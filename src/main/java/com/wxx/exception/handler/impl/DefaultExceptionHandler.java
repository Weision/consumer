package com.wxx.exception.handler.impl;

import com.wxx.exception.handler.AbstractExceptionHandler;

/**
 * 如果异常类 没有对应的handler，通过此hanlder来处理
 * 
 */
public class DefaultExceptionHandler extends AbstractExceptionHandler {

	public DefaultExceptionHandler() {
		this(Exception.class);
	}

	public DefaultExceptionHandler(Class<? extends Exception> clazz) {
		super(clazz);
	}

	@Override
	protected String getErrorCode(Exception ex) {
		return "sap";
	}

}
