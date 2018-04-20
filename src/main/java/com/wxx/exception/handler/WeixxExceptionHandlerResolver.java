package com.wxx.exception.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

/**
 * 异常处理机制
 */
public class WeixxExceptionHandlerResolver extends
		DefaultHandlerExceptionResolver {
	// 每个异常类对应有处理的exceptionHandler
	private ExceptionHandler exceptionHandler;
	// 对于 异常信息 的流 处理
	private ExceptionStreamHandler exceptionStreamHandler;

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		if (this.logger.isErrorEnabled()) {
			this.logger.error(ex.getMessage(), ex);
		}
		ModelAndView mav = super.doResolveException(request, response, handler,
				ex);// 先交由父类处理常见的servlet异常，比如401 402
		if (null == mav) {
			try {
				mav = this.exceptionHandler.handleException(ex, request,
						response, this.exceptionStreamHandler);
			} catch (Exception e) {
				this.logger.warn("Handling of [" + ex.getClass().getName()
						+ "] resulted in Exception", e);
			}
		}
		return mav;
	}

	/**
	 * 重写 父类方法<br/>
	 * 处理 500错误
	 */
	@Override
	protected ModelAndView handleConversionNotSupported(
			ConversionNotSupportedException ex, HttpServletRequest request,
			HttpServletResponse response, Object handlerr) throws IOException {
		this.exceptionStreamHandler.handleException(request, response,
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR + "",
				ex.getMessage());

		return new ModelAndView();
	}

	/**
	 * 处理405错误
	 */
	@Override
	protected ModelAndView handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex,
			HttpServletRequest request, HttpServletResponse response,
			Object handlerr) throws IOException {
		this.exceptionStreamHandler
				.handleException(request, response,
						HttpServletResponse.SC_METHOD_NOT_ALLOWED + "",
						ex.getMessage());
		return new ModelAndView();
	}

	public void setExceptionHandler(ExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	public void setExceptionStreamHandler(
			ExceptionStreamHandler exceptionStreamHandler) {
		this.exceptionStreamHandler = exceptionStreamHandler;
	}
}
