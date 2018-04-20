package com.wxx.constant;

public enum StaticEnum {

	driverClassName("driverClassName"), maxActive("maxActive"), maxIdle(
			"maxIdle"), type("type"), url("www.baidu.com"), username("username"), password(
			"password");
	private String value;

	public String getValue() {
		return this.value;
	}

	private StaticEnum(String value) {
		this.value = value;
	}
}
