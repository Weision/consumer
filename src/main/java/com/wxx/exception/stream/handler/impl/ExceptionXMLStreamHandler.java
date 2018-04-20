package com.wxx.exception.stream.handler.impl;

import javax.servlet.http.HttpServletRequest;

import com.wxx.exception.handler.AbstractExceptionStreamHandler;
import com.wxx.util.StringUtil;

/**
 * 当Media类型 为xml
 */
public class ExceptionXMLStreamHandler extends AbstractExceptionStreamHandler {

	/**
	 * 构造错误信息 的xml报文
	 */
	@Override
	protected String responseMediaStream(HttpServletRequest request,
			String errorCode, String errorMsg) {
		StringBuilder sb = new StringBuilder();
		//头标签
		String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		//正文body标签
		String body = StringUtil.addTag("msgcde", errorCode, null) + StringUtil.addTag("rtnmsg", errorMsg, null);
		//自定义的weixx标签
		String weixx = StringUtil.addTag("body", body, null);
		
		//拼接
		sb.append(header);
		sb.append(StringUtil.addTag("weixx",weixx, null));
		
		//返回结果
		String result = sb.toString();
		return result;
	}

}
