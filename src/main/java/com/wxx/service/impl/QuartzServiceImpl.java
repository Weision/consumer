package com.wxx.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzServiceImpl {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(QuartzServiceImpl.class);

	public void executes() throws Exception {
		LOGGER.info("--- 定时任务开始 ---");

		// 开启线程（1）
		Thread OneThread = new Thread(new SchoolInfoRunnable("www.github.com"));
		OneThread.start();

		// 开启线程（2）
		Thread TwoThread = new Thread(new InstituteInfoRunnable(
				"www.gitbook.com"));
		TwoThread.start();
	}

	class SchoolInfoRunnable implements Runnable {

		private String url;

		public SchoolInfoRunnable(String url) {
			this.url = url;
		}

		@Override
		public void run() {
			Thread.currentThread().setName("1");
			System.out.println(url + "-----1-----");
		}
	}

	class InstituteInfoRunnable implements Runnable {

		private String url;

		public InstituteInfoRunnable(String url) {
			this.url = url;
		}

		@Override
		public void run() {
			Thread.currentThread().setName("2");
			System.out.println(url + "-----2-----");
		}
	}
}
