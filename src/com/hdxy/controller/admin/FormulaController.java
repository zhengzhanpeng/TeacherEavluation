package com.hdxy.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hdxy.mapper.SomeMessageMapper;
import com.hdxy.util.ReturnMessageUtil;

@Controller
@RequestMapping("/admin")
public class FormulaController {
	
	@Autowired
	private SomeMessageMapper someMessageMapper;
	
	@RequestMapping("/formula")
	public String getFormula() {
		return "admin/formula";
	}
	
	@RequestMapping(value = "/compute_semester", method = RequestMethod.GET)
	public String computeSemester() {
		Integer semester = null;
		Integer year = null;
		try {
			semester = Integer.parseInt(someMessageMapper.getValueByName("semester"));
			year = Integer.parseInt(someMessageMapper.getValueByName("year"));
		} catch (NumberFormatException e) {
			return ReturnMessageUtil.MESSAGE_NOT_NUMBER;
		}
		if(semester == 1) {
			
		} else if(semester == 2) {
			
		}
		return ReturnMessageUtil.SYSTEM_BUSY;
	}
	
	public String computeSemester1(int year) {
		
		return ReturnMessageUtil.TRUE;
	}
}









