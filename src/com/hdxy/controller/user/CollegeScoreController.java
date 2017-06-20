package com.hdxy.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdxy.mapper.EndScoreMapper;
import com.hdxy.mapper.Semester1Mapper;
import com.hdxy.mapper.Semester2Mapper;
import com.hdxy.pojo.EndScore;
import com.hdxy.pojo.Semester1;
import com.hdxy.pojo.Semester2;
import com.hdxy.util.MainUtil;

@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"collegeId"}, types = {Integer.class})
public class CollegeScoreController {
	
	@Autowired
	private Semester1Mapper semester1Mapper;
	
	@Autowired
	private Semester2Mapper semester2Mapper;
	
	@Autowired
	private EndScoreMapper endScoreMapper;
	
	@RequestMapping(value = "/college_score", method = RequestMethod.GET)
	public String getCollegeMessage() {
		return "user/college_score";
	}
	
	@RequestMapping(value = "/get_semester1s", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String getSemester1(@ModelAttribute("collegeId") int collegeId) {
		List<Semester1> semester1List = semester1Mapper.getSemester1s(collegeId);
		String str = MainUtil.getJsonToTable(semester1List);
		return str;
	}
	
	@RequestMapping(value = "/get_semester2s", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String getSemester2(@ModelAttribute("collegeId") int collegeId) {
		List<Semester2> semester2List = semester2Mapper.getSemester2s(collegeId);
		String str = MainUtil.getJsonToTable(semester2List);
		return str;
	}
	
	@RequestMapping(value = "/get_end_scores", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String getEndScore(@ModelAttribute("collegeId") int collegeId) {
		List<EndScore> endScoreList = endScoreMapper.getEndScores(collegeId);
		String str = MainUtil.getJsonToTable(endScoreList);
		return str;
	}
}
