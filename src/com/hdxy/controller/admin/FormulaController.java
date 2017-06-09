package com.hdxy.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hdxy.mapper.FormulaMapper;
import com.hdxy.mapper.Semester1Mapper;
import com.hdxy.mapper.Semester2Mapper;
import com.hdxy.mapper.SomeMessageMapper;
import com.hdxy.util.ReturnMessageUtil;

@Controller
@RequestMapping("/admin")
public class FormulaController {
	
	@Autowired
	private SomeMessageMapper someMessageMapper;
	
	@Autowired
	private FormulaMapper formulaMapper;
	
	@Autowired
	private Semester1Mapper semester1Mapper;
	
	@Autowired
	private Semester2Mapper semester2Mapper;
	
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
	
	/**
	 * 计算学期1的期末成绩
	 * @param year
	 * @return
	 */
	public String computeSemester1(int year) {
		Double superviseScore = formulaMapper.getValue("superviseScore", "semester1");
		Double peerScore = formulaMapper.getValue("peerScore", "semester1");
		Double studentScore = formulaMapper.getValue("studentScore", "semester1");
		if(superviseScore == null || peerScore == null || studentScore == null) {
			return ReturnMessageUtil.FORMULA_WRONG;
		}
		int result = semester1Mapper.computeEndScore(superviseScore, peerScore, studentScore, year);
		return ReturnMessageUtil.TRUE;
	}
	
	/**
	 * 计算学期2的期末成绩
	 * @param year
	 * @return
	 */
	public String computeSemester2(int year) {
		Double superviseScore = formulaMapper.getValue("superviseScore", "semester2");
		Double peerScore = formulaMapper.getValue("peerScore", "semester2");
		Double studentScore = formulaMapper.getValue("studentScore", "semester2");
		if(superviseScore == null || peerScore == null || studentScore == null) {
			return ReturnMessageUtil.FORMULA_WRONG;
		}
		int result = semester2Mapper.computeEndScore(superviseScore, peerScore, studentScore, year);
		return ReturnMessageUtil.TRUE;
	}
	
	public String computeEndScore(int year) {
		double endScore1 = semester1Mapper.getAvgEndScore(year);
		double endScore2 = semester2Mapper.getAvgEndScore(year);
		
		return ReturnMessageUtil.TRUE;
	}
}









