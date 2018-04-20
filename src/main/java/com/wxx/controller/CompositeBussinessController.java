package com.wxx.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompositeBussinessController {

	/**
	 * 组合模式
	 * 用以解决不同主站的调用
	 * 主站不同 链路不同 组装的数据不同 故需要来开辟分支
	 * 新增链路时 需要新增分支
	 */
	@Resource(name = "commonControllerList")
	private List<BussinesController> commonControllerList;

	@RequestMapping(value = "/**", produces = "text/plain;charset=UTF-8")
	public String handler(@RequestHeader HttpHeaders header,
			@RequestBody String body, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, String> uriVariables) {
		JSONObject json = JSONObject.fromObject(body);
		String url = json.getString("url");
		String questBody = json.getString("body");
		String handler = "";
		for (BussinesController controller : commonControllerList) {
			if (controller.getUrl().equals(url)) {
				handler = controller.handler(questBody);
			}
		}
		return handler;
	}
}
