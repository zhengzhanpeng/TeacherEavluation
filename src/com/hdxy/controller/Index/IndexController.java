package com.hdxy.controller.Index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdxy.mapper.CollegeMapper;
import com.hdxy.mapper.EndScoreMapper;
import com.hdxy.mapper.Semester1Mapper;
import com.hdxy.mapper.Semester2Mapper;
import com.hdxy.mapper.SomeMessageMapper;
import com.hdxy.mapper.WisdomMapper;
import com.hdxy.pojo.College;
import com.hdxy.pojo.EndScore;
import com.hdxy.pojo.Semester1;
import com.hdxy.pojo.Semester2;
import com.hdxy.util.MainUtil;
import com.hdxy.util.ReturnMessageUtil;

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
	
	@Autowired
	private SomeMessageMapper someMessageMapper;
	
	@Autowired
	private WisdomMapper wisdomMapper;
	
	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public String getIndex(ModelMap model) {
		List<College> list = collegeMapper.getColleges();
		model.addAttribute("list", list);
		return "index/index";
	}
	
	@RequestMapping(value = "/college_score", method = RequestMethod.GET)
	public String getCollegeMessage(@RequestParam int collegeId, ModelMap model) {
		model.addAttribute("collegeName", collegeMapper.getCollegeName(collegeId));
		model.addAttribute("collegeId", collegeId);
		model.addAttribute("wisdom", wisdomMapper.getValueRandom());
		return "index/college_score";
	}
	
	@RequestMapping(value = "/get_semester1s", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String getSemester1(@RequestParam int collegeId) {
		String strNum = someMessageMapper.getValueByName("semester1Num");
		Integer number = 0;
		try {
			number = Integer.parseInt(strNum);
		} catch (NumberFormatException e) {
			return ReturnMessageUtil.MESSAGE_NOT_NUMBER;
		}
		List<Semester1> semester1List = semester1Mapper.getSemester1sToIndex(collegeId, number);
		String str = MainUtil.getJsonToTable(semester1List);
		return str;
	}
	
	@RequestMapping(value = "/get_semester2s", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String getSemester2(@RequestParam int collegeId) {
		String strNum = someMessageMapper.getValueByName("semester2Num");
		Integer number = 0;
		try {
			number = Integer.parseInt(strNum);
		} catch (NumberFormatException e) {
			return ReturnMessageUtil.MESSAGE_NOT_NUMBER;
		}
		List<Semester2> semester2List = semester2Mapper.getSemester2sToIndex(collegeId, number);
		String str = MainUtil.getJsonToTable2(semester2List);
		return str;
	}
	
	@RequestMapping(value = "/get_end_scores", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String getEndScore(@RequestParam int collegeId) {
		String strNum = someMessageMapper.getValueByName("endScoreNum");
		Integer number = 0;
		try {
			number = Integer.parseInt(strNum);
		} catch (NumberFormatException e) {
			return ReturnMessageUtil.MESSAGE_NOT_NUMBER;
		}
		List<EndScore> endScoreList = endScoreMapper.getEndScoresToIndex(collegeId, number);
		String str = MainUtil.getJsonToTable3(endScoreList);
		return str;
	}
}
