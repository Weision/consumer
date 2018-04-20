package com.wxx.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wxx.cache.InitCache;
import com.wxx.cache.WeixxCache;
import com.wxx.entity.Article;
import com.wxx.entity.Body;
import com.wxx.entity.Weixx;
import com.wxx.mapper.WeixxMapper;
import com.wxx.service.IWeixxService;
import com.wxx.service.WeixxService;

/**
 * 通过web请求触发通过mybatis配置文件的数据库查询
 * @名称： CaseController
 * @简述：case接口类
 * @author weixx
 * @createTime 2017-7-12 15:07:35
 */
@RestController
public class WxxController {
	private static Logger logger = Logger.getLogger(WxxController.class);

	@Autowired
	WeixxCache weixxCache;

	@Autowired
	WeixxMapper weixxMapper;

	@Autowired
	WeixxService weixxService;

	@Resource
	IWeixxService iweixxService;

	/**
	 * 读取缓存并查询 直接调mapper接口
	 * 
	 * @return
	 */
	@RequestMapping("/c1")
	public String getC1() {
		// 获取某个缓存配置需要预先知道key
		logger.info(">>weixxCache>>yy>>" + weixxCache.getProperty("yy"));
		// 查询id为1的数据
		Weixx weixx = weixxMapper.selectWeixxById("1");
		logger.info(">>" + weixx.getUsername());
		System.out.println(">>" + weixx.getUsername());
		return weixx.getUsername();
	}

	/**
	 * 通过service服务来调数据库 建议这样来 便于管理 松耦合
	 * 
	 * @return
	 */
	@RequestMapping("/c2")
	public String getC2() {
		Weixx weixx = weixxService.getC1Serv("2");
		return weixx.getUsername();
	}

	/**
	 * 多个参数 利用 Mybatis 自身的多个参数传递方式去做
	 * 
	 * 
	 */
	@RequestMapping("/{c3}/{c4}")
	public String getC4(@RequestHeader HttpHeaders header,
			@RequestBody String body, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, String> uriVariables,
			@PathVariable String frage, @PathVariable String asdasd) {
		Weixx weixx = weixxService.getC1Serv("2", "pp");
		return weixx.getUsername();
	}

	// 入参利用 hashMap 传递多个参数
	@RequestMapping("/c5")
	public String getC5() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "1");
		map.put("username", "rr");
		Weixx weixx = weixxService.getC1Serv(map);
		return weixx.getUsername();
	}

	// 用 association 来得到关联的用户，这是多对一的情况
	@RequestMapping("/c6")
	public String getC6() {

		List<Article> Articles = weixxService.getArticles("1");
		StringBuffer sb = new StringBuffer();
		for (Article article : Articles) {
			sb.append(">>" + article.getContent());
			System.out.println(">>4>>" + article.getContent());
		}
		return sb.toString();
	}

	// 将 association 中对应的映射独立抽取出来，可以达到复用的目的
	@RequestMapping("/c7")
	public String getC7() {

		List<Article> Articles = weixxService.getArticles2("1");
		StringBuffer sb = new StringBuffer();
		for (Article article : Articles) {
			sb.append("aa>>" + article.getWeixx().getUsername());
			System.out.println(">>4>>" + article.getWeixx().getUsername());
		}
		return sb.toString();
	}

	@RequestMapping("/c8")
	public void add() {

		Weixx weixx = new Weixx();
		weixx.setId("Weixx");
		weixx.setUserid("Moer");
		weixx.setUsername("Jing");
		weixxService.add(weixx);
	}

	@RequestMapping("/c9")
	public void update() {
		Weixx weixx = new Weixx();
		weixx.setId("Weixx");
		weixx.setUserid("Moer");
		weixx.setUsername("eeeeee");
		weixxService.update(weixx);
	}

	@RequestMapping("/c10")
	public void delete() {
		weixxService.delete("1");
	}

	/*
	 * 上送中包含对象的话有两种场景 1 在方法入参中直接传入对象
	 * 前端上送时要不要设置contentType或设置成x-www-form-unencoded 2 在方法入参中@RequestBody+传入对象
	 * 前端上送时在body中上送 要设置contentType或设置成application/json
	 */
	@RequestMapping(value = "/c11", method = RequestMethod.POST)
	public String getC11(@RequestBody Body body) {
		logger.info("++++" + body.getMethod());
		logger.trace("Trace Message!");
		logger.debug("Debug Message!");
		logger.info("Info Message!");
		logger.warn("Warn Message!");
		logger.error("Error Message!");
		logger.fatal("Fatal Message!");
		Weixx weixx = weixxService.getC1Serv("2", "pp");
		System.out.println(">>" + weixx.getUsername());
		logger.info(">>" + weixx.getUsername());
		return weixx.getUsername();
	}

	@RequestMapping(value = "/c12", method = RequestMethod.POST)
	public String getC12() {
		Map<String, String> wMap = InitCache.getWxxMap();
		System.out.println("wMap>>" + wMap.toString());
		return wMap.toString();
	}

	@RequestMapping(value = "/c13", method = RequestMethod.POST)
	public String getC13() {
		Map<String, String> wMap = InitCache.getWxxMap();
		System.out.println("wMap>>" + wMap.toString());
		return wMap.toString();
	}

	/**
	 * dubbo调用 无参的方法
	 * 
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/c14", method = RequestMethod.POST)
	public String getC14() throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		@SuppressWarnings("rawtypes")
		Class cl = IWeixxService.class;
		Method method = cl.getDeclaredMethod("getC1Serv");
		Object invoke = method.invoke(iweixxService);
		return (String) invoke;
	}

	/**
	 * dubbo调用 入参为一个字符串 返回一个对象
	 * 
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/c15", method = RequestMethod.POST)
	public String getC15() throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		@SuppressWarnings("rawtypes")
		Class cl = IWeixxService.class;
		// 第一个方法一个入参
		Method method = cl.getDeclaredMethod("getC1Serv",
				java.lang.String.class);
		Object invoke = method.invoke(iweixxService, "1");
		// 第二个方法两个入参
		Method method1 = cl.getDeclaredMethod("getC1Serv",
				java.lang.String.class, java.lang.String.class);
		Object invoke1 = method1.invoke(iweixxService, "3", "vv");

		Weixx weixx = (Weixx) invoke;
		Weixx weixx1 = (Weixx) invoke1;
		String name = weixx.getUsername();
		String name1 = weixx1.getUsername();
		return name + "-----" + name1;
	}

	/**
	 * dubbo调用 入参为一个字符串 返回一个对象
	 * 
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/c16", method = RequestMethod.POST)
	public String getC16(@RequestBody Weixx weixx)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		@SuppressWarnings("rawtypes")
		Class cl = IWeixxService.class;
		Method method = cl.getDeclaredMethod("add", Weixx.class);
		Object invoke = method.invoke(iweixxService, weixx);
		Integer i = (Integer) invoke;
		return i.toString();
	}
	

}
