package com.wxx.util;

import org.springframework.web.context.WebApplicationContext;

public class SpringUtil {
	private static WebApplicationContext webApplicationContext;

	public static Object getBean(String beanName) {
		return SpringUtil.webApplicationContext.getBean(beanName);
	}

	public static <T> T getBean(String beanName, Class<T> requiredType) {
		return SpringUtil.webApplicationContext.getBean(beanName, requiredType);
	}

	public static boolean isReady() {
		return null != SpringUtil.webApplicationContext;
	}

	public static void setWebApplicationContext(
			WebApplicationContext webApplicationContext) {
		SpringUtil.webApplicationContext = webApplicationContext;
	}
}
