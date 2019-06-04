package com.cafe24.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	public MainController() {
		System.out.println("MainController 생성");
	}
	
	@RequestMapping({"/","/main"})
	public String index() {
		return "main/index";
	}
	
}
