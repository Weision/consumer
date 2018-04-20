package com.wxx.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wxx.filter.Filter;

public class SystemService {
	// 上下文中已经定义bean SystemService
	// 利用静态代码块触发加载动态过滤器
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Object obj = context.getBean("dynamicAgent");
		((Filter) obj).doFilter("erBao");
	}
	
	//第二种方法是利用初始化方法来加载
	@SuppressWarnings("unused")
	private void loadFilter() {
		System.out.println("初始化方法来加载");
	}
}
