package com.wxx.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * 异常操作接口<br/>
 * 获取错误码和错误信息
 * 
 */
public interface ExceptionHandler {
	public ModelAndView handleException(Exception ex,
			HttpServletRequest request, HttpServletResponse response,
			ExceptionStreamHandler handler);
}
