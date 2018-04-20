package com.wxx.composite.controller;

import net.sf.json.JSONObject;

import com.wxx.controller.AbstractController;

public class TeacherController extends AbstractController {

	private String url = "/teacher";

	public String hello() {
		JSONObject json = new JSONObject();
		json.put("老师", "苍井空");
		return json.toString();
	}

	@Override
	public String getUrl() {
		return this.url;
	}

}
