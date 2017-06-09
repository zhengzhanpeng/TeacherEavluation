package com.hdxy.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hdxy.mapper.CollegeMapper;
import com.hdxy.pojo.College;
import com.hdxy.util.ReturnMessageUtil;

@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"collegeId"}, types = {Integer.class})
public class CollegeMessageController {
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	@RequestMapping(value = "/college_information", method = RequestMethod.GET)
	public ModelAndView getCollegeMessage(@ModelAttribute("collegeId") Integer collegeId) {
		ModelAndView mav = new ModelAndView("user/college_information");
		College college = collegeMapper.getCollegeByCollegeId(collegeId);
		mav.addObject("college", college);
		return mav;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView tese(@ModelAttribute("collegeId") Integer collegeId) {
		ModelAndView mav = new ModelAndView("user/test");
		return mav;
	}
	
	@RequestMapping(value = "/set_phone", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String setPhone(@RequestParam String phone, @ModelAttribute("collegeId") int collegeId) {
		int result = collegeMapper.setPhone(phone, collegeId);
		if(result == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
	}
}
