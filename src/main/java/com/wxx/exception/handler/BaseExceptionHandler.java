/**
 * @文件名：BaseExceptionController.java
 * @类名:com.bocsoft.sap.base.handler.impl.BaseExceptionController
 * @Author:朱正伟
 * @创建时间：2013-11-3 下午1:55:14
 */
package com.wxx.exception.handler;

import com.wxx.exception.BaseException;

/**
 * 处理sap平台基础异常类 BaseException
 * 
 */
public class BaseExceptionHandler extends AbstractExceptionHandler {

	public BaseExceptionHandler() {
		this(BaseException.class);
	}

	public BaseExceptionHandler(Class<? extends Exception> clazz) {
		super(clazz);
	}

	@Override
	protected String getErrorCode(Exception ex) {
		BaseException e = (BaseException) ex;
		return e.getCode();
	}

	@Override
	protected String getErrorMsg(Exception ex) {
		@SuppressWarnings("unused")
		BaseException e = (BaseException) ex;
		return "json串";
	}

}
