package com.hdxy.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hdxy.mapper.EndScoreMapper;
import com.hdxy.mapper.FormulaMapper;
import com.hdxy.mapper.Semester1Mapper;
import com.hdxy.mapper.Semester2Mapper;
import com.hdxy.mapper.SomeMessageMapper;
import com.hdxy.pojo.ScoreToCompute;
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
	
	@Autowired
	private EndScoreMapper endScoreMapper;
	
	@RequestMapping("/formula")
	public String getFormula() {
		return "admin/formula";
	}
	
	@RequestMapping(value = "/compute_semester", method = RequestMethod.GET)
	public synchronized String computeSemester() {
		Integer semester = null;
		Integer year = null;
		String result;
		try {
			semester = Integer.parseInt(someMessageMapper.getValueByName("semester"));
			year = Integer.parseInt(someMessageMapper.getValueByName("year"));
		} catch (NumberFormatException e) {
			return ReturnMessageUtil.MESSAGE_NOT_NUMBER;
		}
		if(semester == 1) {
			result = computeSemester1(year);
			return result;
		} else if(semester == 2) {
			result = computeSemester2(year);
			computeEndScore(year);
			return result;
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
		if(result == 0) return ReturnMessageUtil.NOT_COMPUTE;
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
		if(result == 0) return ReturnMessageUtil.NOT_COMPUTE;
		return ReturnMessageUtil.TRUE;
	}
	
	public String computeEndScore(int year) {
		double endScore1AVG = semester1Mapper.getAvgEndScore(year);
		double endScore2AVG = semester2Mapper.getAvgEndScore(year);
		double endScore1MUL = formulaMapper.getValue("endScore1", "m1");
		double endScore2MUL = formulaMapper.getValue("endScore2", "m1");
		double teachScoreMUL = formulaMapper.getValue("teachScore", "m2");
		double m1MUL = formulaMapper.getValue("m1", "all");
		double m2MUL = formulaMapper.getValue("m2", "all");
		double teachScore = 0;
		Date date = new Date();
		List<ScoreToCompute> list = endScoreMapper.geScoreToCompute(year);
		ScoreToCompute stc;
		int result;
		for (int i = 0; i < list.size(); i++) {
			stc = list.get(i);
			if(stc.getEndScore1() == null) stc.setEndScore1(endScore1AVG);
			if(stc.getEndScore2() == null) stc.setEndScore2(endScore2AVG);
			if(stc.getTeachScore() == null) stc.setTeachScore(teachScore);
			stc.setEndScore1MUL(endScore1MUL);
			stc.setEndScore2MUL(endScore2MUL);
			stc.setTeachScoreMUL(teachScoreMUL);
			stc.setM1MUL(m1MUL);
			stc.setM2MUL(m2MUL);
			stc.setYear(year);
			result = endScoreMapper.setEndScore(stc);
			if(result != 0) continue;
			stc.setDate(date);
			endScoreMapper.addEndScore(stc);
		}
		return ReturnMessageUtil.TRUE;
	}
}









