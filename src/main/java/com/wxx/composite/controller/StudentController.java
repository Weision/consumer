package com.wxx.composite.controller;

import net.sf.json.JSONObject;

import com.wxx.controller.AbstractController;

public class StudentController extends AbstractController {

	private String url = "/user";

	public String hello() {
		JSONObject json = new JSONObject();
		json.put("学生", "郝勇");
		return json.toString();
	}

	@Override
	public String getUrl() {
		return this.url;
	}
}
