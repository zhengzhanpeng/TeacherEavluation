package com.hdxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnyController {
	
	@RequestMapping("/*")
	public String toIndex() {
		return "redirect:/index/index"; 
	}
	
	@RequestMapping("/index/*")
	public String toIndex1() {
		return "redirect:/index/index"; 
	}
	
	@RequestMapping("/user/*")
	public String toIndex2() {
		return "redirect:/user/index"; 
	}
	
	@RequestMapping("/admin/*")
	public String toIndex3() {
		return "redirect:/admin/index"; 
	}
}
