package com.hdxy.controller.user;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdxy.mapper.CollegeMapper;
import com.hdxy.mapper.Semester2Mapper;
import com.hdxy.mapper.SomeMessageMapper;
import com.hdxy.pojo.EndScoreInput;

@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"userId", "collegeId"}, types = {Integer.class})
public class MainController {
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	@Autowired
	private SomeMessageMapper someMessageMapper;
	
	@Autowired
	private Semester2Mapper semester2Mapper;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getUserIndex(ModelMap model, @ModelAttribute("collegeId") Integer collegeId) {
		Integer state = collegeMapper.getState(collegeId);
		Integer semester = 0;
		int semesterB = Integer.parseInt(someMessageMapper.getValueByName("semester"));
		int endScoreInputState = 0;
		if (state == null) {
			state = -1;
		}
		if(state == 1) {
			semester = semesterB;
		}
		int size = semester2Mapper.getEndScoreInput(collegeId).size();
		if(semesterB == 2 && state == 2 && size > 0) endScoreInputState = 1;
		String collegeName = collegeMapper.getCollegeName(collegeId);
		model.addAttribute("semester", semester);
		model.addAttribute("collegeName", collegeName);
		model.addAttribute("endScoreInputState", endScoreInputState);
		return "user/index";
	}
	
	@RequestMapping(value = "exit", produces = "text/html;charset=utf-8")
	public String exit(ModelMap model) {
		model.addAttribute("userId", 0);
		return "user/login";
	}
}
