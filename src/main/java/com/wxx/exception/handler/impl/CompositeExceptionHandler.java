package com.wxx.exception.handler.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.wxx.exception.handler.ExceptionHandler;
import com.wxx.exception.handler.ExceptionStreamHandler;

/**
 * 使用组合模式 循环遍历各种exception handler
 * 
 */
public class CompositeExceptionHandler implements ExceptionHandler {
	private List<ExceptionHandler> exceptionHandlerList;
	private ExceptionHandler defaultExceptionHandler;

	public CompositeExceptionHandler() {
	}

	@Override
	public ModelAndView handleException(Exception ex,
			HttpServletRequest request, HttpServletResponse response,
			ExceptionStreamHandler handler) {
		ModelAndView mv = null;
		for (ExceptionHandler exceptionHandler : this.exceptionHandlerList) {
			mv = exceptionHandler.handleException(ex, request, response,
					handler);
			if (mv != null) {
				break;
			}
		}
		// 如果 该异常没有 对应的 exceptionHandler则 通过 defaultExceptionHandler来处理;抛出错误提示信息
		if (mv == null) {
			Exception exception = new Exception("eeeeee",ex);
			mv = this.defaultExceptionHandler.handleException(exception, request,
					response, handler);
		}
		return mv;
	}

	public void setExceptionHandlerList(
			List<ExceptionHandler> exceptionHandlerList) {
		this.exceptionHandlerList = exceptionHandlerList;
	}

	public void setDefaultExceptionHandler(
			ExceptionHandler defaultExceptionHandler) {
		this.defaultExceptionHandler = defaultExceptionHandler;
	}

}
