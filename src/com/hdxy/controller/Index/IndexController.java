package com.hdxy.controller.Index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hdxy.mapper.CollegeMapper;
import com.hdxy.mapper.EndScoreMapper;
import com.hdxy.mapper.Semester1Mapper;
import com.hdxy.mapper.Semester2Mapper;
import com.hdxy.pojo.College;
import com.hdxy.pojo.EndScore;
import com.hdxy.pojo.Semester1;
import com.hdxy.pojo.Semester2;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	@Autowired
	private Semester1Mapper semester1Mapper;
	
	@Autowired
	private Semester2Mapper semester2Mapper;
	
	@Autowired
	private EndScoreMapper endScoreMapper;
	
	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public String getIndex(ModelMap model) {
		List<College> list = collegeMapper.getColleges();
		model.addAttribute("list", list);
		return "index/index";
	}
	
	@RequestMapping(value = "/college_message", method = RequestMethod.GET)
	public String getCollegeMessage(@RequestParam int id, ModelMap model) {
		List<Semester1> semester1List = semester1Mapper.getSemester1s(id);
		List<Semester2> semester2List = semester2Mapper.getSemester2s(id);
		List<EndScore> endScoreList = endScoreMapper.getEndScores(id);
		model.addAttribute("semester1List", semester1List);
		model.addAttribute("semester2List", semester2List);
		model.addAttribute("endScoreList", endScoreList);
		return "index/college_message";
	}
}
