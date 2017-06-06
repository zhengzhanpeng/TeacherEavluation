package com.hdxy.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdxy.mapper.CollegeMapper;
import com.hdxy.pojo.College;
import com.hdxy.util.MainUtil;

@Controller
@RequestMapping("/admin")
public class ScoreInputController {
	
	@Autowired
	private CollegeMapper collegeMapper;

	@RequestMapping(value = "/score_input", method = RequestMethod.GET)
	public String getScoreInput() {
		return "admin/score_input";
	}
	
	/**
	 * 通过学年及学期获取学院信息及当前状态
	 * @param year
	 * @param semester
	 * @return
	 */
	@RequestMapping(value = "/get_colleges_and_state", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String getCollegeMessageByAdmin(@RequestParam int year, @RequestParam int semester) {
		List<College> list = collegeMapper.getCollegesAndState();
		String str = MainUtil.getJsonToTable(list);
		return str;
	}
	
	
}
