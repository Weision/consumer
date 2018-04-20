package com.wxx.constant;

public enum Hello implements IHello {
	hello("hello"), helloChina("你好"), helloJpan("XiYouMaSi");
	private String value;

	public String getValue() {
		return this.value;
	}

	private Hello(String value) {
		this.value = value;
	}
}
