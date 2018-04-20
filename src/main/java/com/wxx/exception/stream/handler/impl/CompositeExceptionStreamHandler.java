package com.wxx.exception.stream.handler.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxx.exception.handler.ExceptionStreamHandler;

/**
 * 组合模式<br/>
 * 循环 遍历 ExceptionStreamHandler
 */
public class CompositeExceptionStreamHandler implements ExceptionStreamHandler {
	private List<ExceptionStreamHandler> exceptionStreamHandlerList;

	public CompositeExceptionStreamHandler() {
		this.exceptionStreamHandlerList = new ArrayList<ExceptionStreamHandler>();
		this.exceptionStreamHandlerList.add(new ExceptionJSONStreamHandler());
		this.exceptionStreamHandlerList.add(new ExceptionXMLStreamHandler());
	}

	@Override
	public String handleException(HttpServletRequest request,
			HttpServletResponse response, String errorCode, String errorMsg) {
		String str = null;
		for (ExceptionStreamHandler exceptionStreamHandler : this.exceptionStreamHandlerList) {
			str = exceptionStreamHandler.handleException(request, response,
					errorCode, errorMsg);
			if (null != str) {
				break;
			}
		}
		return str;
	}

	public void setExceptionStreamHandlerList(
			List<ExceptionStreamHandler> exceptionStreamHandlerList) {
		this.exceptionStreamHandlerList = exceptionStreamHandlerList;
	}

}
