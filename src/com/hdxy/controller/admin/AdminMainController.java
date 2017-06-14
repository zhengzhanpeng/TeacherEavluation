package com.hdxy.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/admin")
@SessionAttributes(value = {"adminId"}, types = {Integer.class})
public class AdminMainController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getUserIndex() {
		return "admin/index";
	}
	
	@RequestMapping(value = "exit", produces = "text/html;charset=utf-8")
	public String exit(ModelMap model) {
		model.addAttribute("adminId", 0);
		return "admin/login";
	}
}
