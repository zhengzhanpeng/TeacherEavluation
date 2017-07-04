package com.hdxy.controller.user;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdxy.mapper.CollegeMapper;
import com.hdxy.mapper.Semester1Mapper;
import com.hdxy.mapper.Semester2Mapper;
import com.hdxy.mapper.SomeMessageMapper;
import com.hdxy.mapper.TeacherDataMapper;
import com.hdxy.pojo.EndScoreInput;
import com.hdxy.pojo.Semester1;
import com.hdxy.pojo.Semester2;
import com.hdxy.pojo.TeacherData;
import com.hdxy.util.MainUtil;
import com.hdxy.util.ReturnMessageUtil;

@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"collegeId"}, types = {Integer.class})
public class EndScoreInputController {
	
	@Autowired
	private SomeMessageMapper someMessageMapper;
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	@Autowired
	private Semester1Mapper semester1Mapper;
	
	@Autowired
	private Semester2Mapper semester2Mapper;
	
	@Autowired
	private TeacherDataMapper teacherDataMapper;
	
	@RequestMapping(value = "/end_score_input", method = RequestMethod.GET)
	public String endScoreInput(@ModelAttribute("collegeId") Integer collegeId) {
		Integer state = collegeMapper.getState(collegeId);
		String semester = someMessageMapper.getValueByName("semester");
		if(state == null || state != 2 || !semester.equals("2")) return "user/main";
		return "user/end_score_input";
	}
	
	/**
	 * 通过学院Id获取要录入的期末成绩信息
	 * @param collegeId
	 * @return
	 */
	@RequestMapping(value = "/end_score_input", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String endScoreInputP(@ModelAttribute("collegeId") Integer collegeId) {
		List<EndScoreInput> list = semester2Mapper.getEndScoreInput(collegeId);
		String str = MainUtil.getJsonToTable(list);
		return str;
	}
	
	@RequestMapping(value = "/save_end_socre", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String saveEndScore(@ModelAttribute EndScoreInput endScoreInput, @ModelAttribute("collegeId") Integer collegeId) {
		int result = 0;
		int year = 0;
		if(endScoreInput.getJobNumber() == null || endScoreInput.getName() == null || endScoreInput.getEndScore() == null || endScoreInput.getSemester() == null) {
			return ReturnMessageUtil.MESSAGE_IS_NULL;
		}
		if(endScoreInput.getEndScore() > 100) {
			return ReturnMessageUtil.SCORE_IS_BIGER;
		}
		if(endScoreInput.getEndScore() < 0) {
			return ReturnMessageUtil.SCORE_IS_SMALL;
		}
		if(endScoreInput.getSemester() != 1 && endScoreInput.getSemester() != 2) {
			return ReturnMessageUtil.SYSTEM_BUSY;
		}
		try {
			year = Integer.parseInt(someMessageMapper.getValueByName("year"));
		} catch (NumberFormatException e) {
			return ReturnMessageUtil.MESSAGE_NOT_NUMBER;
		}
		TeacherData t = teacherDataMapper.getTeacher(endScoreInput.getJobNumber());
		if(endScoreInput.getSemester() == 1) {
			Semester1 semester1 = new Semester1();
			semester1.setDate(new Date());
			semester1.setCollegeId(collegeId);
			semester1.setEndScore(endScoreInput.getEndScore());
			semester1.setJobNumber(endScoreInput.getJobNumber());
			semester1.setName(endScoreInput.getName());
			semester1.setYear(year);
			semester1.setPosition(t.getPosition());
			result = semester1Mapper.updateEndScore(semester1);
			if(result == 0)
				result = semester1Mapper.addSemester1Set(semester1);
		} else {
			Semester2 semester2 = new Semester2();
			semester2.setDate(new Date());
			semester2.setCollegeId(collegeId);
			semester2.setEndScore(endScoreInput.getEndScore());
			semester2.setJobNumber(endScoreInput.getJobNumber());
			semester2.setName(endScoreInput.getName());
			semester2.setYear(year);
			semester2.setPosition(t.getPosition());
			result = semester2Mapper.updateEndScore(semester2);
			if(result == 0)
				result = semester2Mapper.addSemester2Set(semester2);
		}
		if(result == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
	}
}
