package com.spring.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	
	@GetMapping("page1")
	public String page1() throws Exception{
		logger.info("첫 페이지로 이동");
		return "page1";
	}

}
