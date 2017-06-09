package com.hdxy.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdxy.mapper.CollegeMapper;
import com.hdxy.mapper.SomeMessageMapper;

@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"userId", "collegeId"}, types = {Integer.class})
public class MainController {
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	@Autowired
	private SomeMessageMapper someMessageMapper;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getUserIndex(ModelMap model, @ModelAttribute("collegeId") Integer collegeId) {
		Integer state = collegeMapper.getState(collegeId);
		Integer semester = 0;
		if (state == null) {
			state = -1;
		}
		if(state == 1) {
			semester = Integer.parseInt(someMessageMapper.getValueByName("semester"));
		}
		model.addAttribute("semester", semester);
		return "user/index";
	}
}
