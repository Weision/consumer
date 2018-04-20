package com.wxx.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxx.exception.ControllerException;

@RestController
public class ExceptionController {
	@RequestMapping("/exception")
	public String getException(@RequestBody String flag)
			throws ControllerException {
		System.out.println("-----exception----start---");
		if ("1".equals(flag)) {
			throw new ControllerException("110", "这是一个controller exception");
		}
		System.out.println("-----exception----end---");
		return "获取异常结束";
	}
}
