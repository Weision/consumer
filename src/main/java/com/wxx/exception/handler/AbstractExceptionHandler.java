package com.wxx.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理抽象类
 */
public abstract class AbstractExceptionHandler implements ExceptionHandler {
	private final Class<? extends Exception> clazz;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger
			.getLogger(AbstractExceptionHandler.class);

	public AbstractExceptionHandler(Class<? extends Exception> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 获取错误码
	 * 
	 * @param ex
	 *            异常对象
	 * @return
	 */
	protected abstract String getErrorCode(Exception ex);

	/**
	 * 获取错误信息
	 * 
	 * @param ex
	 *            异常对象
	 * @return
	 */
	protected String getErrorMsg(Exception ex) {
		return ex.getMessage();
	}

	/**
	 * 处理异常
	 */
	@Override
	public ModelAndView handleException(Exception ex,
			HttpServletRequest request, HttpServletResponse response,
			ExceptionStreamHandler handler) {
		System.out.println("----------------------");
		if (!this.clazz.isInstance(ex)) {
			return null;
		}

		handler.handleException(request, response, this.getErrorCode(ex),
				this.getErrorMsg(ex));
		return new ModelAndView();
	}

}
