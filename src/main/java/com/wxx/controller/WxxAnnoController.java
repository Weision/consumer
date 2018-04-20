package com.wxx.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wxx.entity.Weixx;
import com.wxx.service.WeixxServiceAnnotation;

@RestController
public class WxxAnnoController {

	@Autowired
	private WeixxServiceAnnotation weixxService;

	@RequestMapping(value = "/hello", produces = "text/plain;charset=UTF-8")
	public String hello() {
		JSONObject json = new JSONObject();
		json.put("土豪", "壕勇");
		json.put("老师", "苍井空");
		return json.toString();
	}

	/**
	 * 查询单个数据
	 */
	@RequestMapping("/weixx")
	public String getWeixx(@RequestHeader HttpHeaders header,
			@RequestBody String body, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, String> uriVariables) {
		JSONObject json = JSONObject.fromObject(body);
		String name = json.getString("name");
		Weixx weixx = weixxService.queryWeixx(name);
		json.put("name", name);
		System.out.println("my name is :" + weixx.getUsername());
		response.setCharacterEncoding("utf-8");
		return json.toString();
	}

	/**
	 * 查询单个数据
	 */
	@RequestMapping("/weixx_params")
	public String getWeixxParams(@RequestHeader HttpHeaders header,
			@RequestBody String body, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, String> uriVariables) {
		JSONObject json = JSONObject.fromObject(body);
		String name = json.getString("name");
		String id = json.getString("id");
		Weixx weixx = weixxService.queryWeixxParams(name, id);
		System.out.println("my name is :" + weixx.getUsername());
		return weixx.getUsername();
	}

	/**
	 * 查询整个表
	 * 
	 */
	@RequestMapping("/weixx_list")
	public String getWeixxList() {

		List<Weixx> weixxList = weixxService.queryWeixxList();
		StringBuffer sb = new StringBuffer();
		for (Weixx weixx : weixxList) {
			sb.append("|" + weixx.getUsername());
			System.out.println("my name is :" + weixx.getUsername());
		}
		return sb.toString();
	}

	/**
	 * 已字符换形式保存数据
	 * 
	 */
	@RequestMapping("/save")
	public int save(@RequestBody String body) {
		JSONObject json = JSONObject.fromObject(body);
		String name = json.getString("name");
		String id = json.getString("id");
		String userId = json.getString("userid");

		int result = weixxService.insert(id, userId, name);
		return result;
	}

	/**
	 * 已字对象形式保存数据
	 * 
	 */
	@RequestMapping("/save_weixx")
	public int saveWeixx(@RequestBody String body) {
		JSONObject json = JSONObject.fromObject(body);
		String name = json.getString("name");
		String id = json.getString("id");
		String userId = json.getString("userid");
		Weixx weixx = new Weixx();
		weixx.setId(id);
		weixx.setUserid(userId);
		weixx.setUsername(name);

		int result = weixxService.insertWeixx(weixx);
		return result;
	}

	/**
	 * 删除数据
	 * 
	 */
	@RequestMapping("/delete")
	public int delete(@RequestBody String body) {
		JSONObject json = JSONObject.fromObject(body);
		String name = json.getString("name");
		String id = json.getString("id");
		String userId = json.getString("userid");
		Weixx weixx = new Weixx();
		weixx.setId(id);
		weixx.setUserid(userId);
		weixx.setUsername(name);

		int result = weixxService.delete(weixx);
		return result;
	}

	/**
	 * 删除数据
	 * 
	 */
	@RequestMapping("/update")
	public int update(@RequestBody String body) {
		JSONObject json = JSONObject.fromObject(body);
		String name = json.getString("name");
		String id = json.getString("id");
		String userId = json.getString("userid");
		Weixx weixx = new Weixx();
		weixx.setId(id);
		weixx.setUserid(userId);
		weixx.setUsername(name);

		int result = weixxService.update(weixx);
		return result;
	}

}
