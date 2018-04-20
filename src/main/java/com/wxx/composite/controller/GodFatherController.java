package com.wxx.composite.controller;

import net.sf.json.JSONObject;

import com.wxx.controller.AbstractController;

public class GodFatherController extends AbstractController {
	private String url = "/hello";

	public String hello() {
		JSONObject json = new JSONObject();
		json.put("教父", "张学友");
		return json.toString();
	}

	@Override
	public String getUrl() {
		return this.url;
	}

}
