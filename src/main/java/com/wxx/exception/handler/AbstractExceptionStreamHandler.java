package com.wxx.exception.handler;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 异常处理 机制 抽象类 <br/>
 * 将异常 信息 通过流的方式 发送给web请求端
 * 
 */
public abstract class AbstractExceptionStreamHandler implements
		ExceptionStreamHandler {
	private static final Log LOGGER = LogFactory
			.getLog(AbstractExceptionStreamHandler.class);

	@Override
	public String handleException(HttpServletRequest request,
			HttpServletResponse response, String errorCode, String errorMsg) {

		PrintWriter write = null;
		String str = null;
		try {
			response.setHeader("msgcde", errorCode);
			response.setHeader("returnmsg", errorMsg);// 将错误 信息 放在报文头
			str = this.responseMediaStream(request, errorCode, errorMsg);
			if (null == str) {
				return null;
			}
			write = response.getWriter();
			write.write(str);
			write.flush();
			write.close();
		} catch (Exception e) {
			if (AbstractExceptionStreamHandler.LOGGER.isErrorEnabled()) {
				AbstractExceptionStreamHandler.LOGGER.error(e.getMessage(), e);
			}
			if (null != write) {
				write.close();
			}
		}

		return str;
	}

	/**
	 * 根据错误码和错误信息构造相对应的media报文(如 json\xml)
	 * 
	 * @param errorCode
	 *            错误码
	 * @param errorMsg
	 *            错误信息
	 * @return
	 */
	protected abstract String responseMediaStream(HttpServletRequest request,
			String errorCode, String errorMsg);

}
