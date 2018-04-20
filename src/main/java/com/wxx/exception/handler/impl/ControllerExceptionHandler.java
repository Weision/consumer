/**
 * @文件名：BaseExceptionController.java
 * @类名:com.bocsoft.sap.base.handler.impl.BaseExceptionController
 * @Author:朱正伟
 * @创建时间：2013-11-3 下午1:55:14
 */
package com.wxx.exception.handler.impl;

import com.wxx.exception.ControllerException;
import com.wxx.exception.handler.AbstractExceptionHandler;

/**
 * 处理异常类 ControllerException
 * 
 */
public class ControllerExceptionHandler extends AbstractExceptionHandler {

	public ControllerExceptionHandler() {
		this(ControllerException.class);
	}

	public ControllerExceptionHandler(Class<? extends Exception> clazz) {
		super(clazz);
	}

	@Override
	protected String getErrorCode(Exception ex) {
		ControllerException e = (ControllerException) ex;
		return e.getCode();
	}

	@Override
	protected String getErrorMsg(Exception ex) {
		@SuppressWarnings("unused")
		ControllerException e = (ControllerException) ex;
		return "json串";
	}

}
