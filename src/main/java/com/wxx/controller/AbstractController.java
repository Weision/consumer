package com.wxx.controller;

public abstract class AbstractController implements BussinesController {

	@Override
	public String handler(String httpBody) {
		String body = buildBody(httpBody);
		return body;
	}

	/**
	 * 处理主逻辑
	 */
	protected String buildBody(String httpBody) {

		System.out.println("处理主逻辑开始: " + httpBody);
		// 组装数据
		String jsonStr = this.buildResponseBody(httpBody);
		// 调用主站
		String responseData = this.getResponseData(jsonStr);
		// 返回数据
		String resToClient = this.resToClient(responseData);

		System.out.println("处理主逻辑结束: " + resToClient);
		return resToClient;
	}

	protected String getResponseData(String httpBody) {
		System.out.println("请求返回数据: " + httpBody);
		return httpBody;

	}

	/**
	 * 发送请求数据
	 */
	protected String resToClient(String httpBody) {
		System.out.println("发送请求数据: " + httpBody);
		return httpBody;
	}

	/**
	 * 组装响应体
	 */
	protected String buildResponseBody(String a) {
		System.out.println("组装响应体: " + a);
		return a.toString();
	}
}
