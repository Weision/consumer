package com.wxx.controller;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisCluster;

@RestController
public class RedisController {
	private static Logger logger = Logger.getLogger(RedisController.class);
	private static ApplicationContext context = new ClassPathXmlApplicationContext(
			new String[] { "applicationContext.xml" });

	private static JedisCluster jedisCluster = (JedisCluster) context
			.getBean("jedisCluster");

	@RequestMapping("/redis")
	public String getC1() {
		jedisCluster.append("ni", "mei");
		logger.info(">>---------------");
		String string = jedisCluster.get("ni");
		logger.info(">>---------------" + string);

		return string;
	}
}
