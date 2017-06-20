package com.hdxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnyController {
	
	@RequestMapping("/*")
	public String toIndex() {
		return "redirect:/index/index"; 
	}
}
