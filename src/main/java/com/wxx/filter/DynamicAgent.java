package com.wxx.filter;

public class DynamicAgent implements Filter {

	@Override
	public void doFilter(String str) {
		System.out.println("-------" + str);
	}
}
