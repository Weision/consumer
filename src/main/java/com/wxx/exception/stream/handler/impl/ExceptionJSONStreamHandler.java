package com.wxx.exception.stream.handler.impl;

import javax.servlet.http.HttpServletRequest;

import com.wxx.exception.handler.AbstractExceptionStreamHandler;

import net.sf.json.JSONObject;

/**
 * 当 Media类型 为 json类型
 * 
 */
public class ExceptionJSONStreamHandler extends AbstractExceptionStreamHandler {

	/**
	 * 构造错误信息的 json报文
	 */
	@Override
	protected String responseMediaStream(HttpServletRequest request,
			String errorCode, String errorMsg) {
		JSONObject obj = new JSONObject();
		obj.put("msgcde", errorCode);
		obj.put("returnmsg", errorMsg);
		return obj.toString();
	}

}
