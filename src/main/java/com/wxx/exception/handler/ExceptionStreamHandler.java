package com.wxx.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常信息 流处理
 */
public interface ExceptionStreamHandler {
	/**
	 * 处理异常
	 * 
	 * @param request
	 *            http请求对象
	 * @param response
	 *            http响应对象
	 * @param errorCode
	 *            错误码
	 * @param errorMsg
	 *            错误信息
	 * @param mediaType
	 *            流写出的数据类型
	 */
	String handleException(HttpServletRequest request,
			HttpServletResponse response, String errorCode, String errorMsg);
}
