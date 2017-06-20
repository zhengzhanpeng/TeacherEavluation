package com.hdxy.controller.admin;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hdxy.mapper.CollegeMapper;
import com.hdxy.mapper.Semester1Mapper;
import com.hdxy.mapper.Semester2Mapper;
import com.hdxy.mapper.SomeMessageMapper;
import com.hdxy.mapper.TeacherDataMapper;
import com.hdxy.pojo.ScoreInput;
import com.hdxy.pojo.ScoreInputShow;
import com.hdxy.util.MainUtil;
import com.hdxy.util.ReturnMessageUtil;

@Controller
@RequestMapping("/admin")
public class ScoreInputController {
	
	@Autowired
	private SomeMessageMapper someMessageMapper;
	
	@Autowired
	private Semester1Mapper semester1Mapper;
	
	@Autowired
	private Semester2Mapper semester2Mapper;
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	@Autowired
	private TeacherDataMapper teacherDataMapper;

	@RequestMapping(value = "/score_input", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	public ModelAndView getScoreInput() {
		ModelAndView mav = new ModelAndView("admin/score_input");
		List<String> list = semester2Mapper.getEndScoreCollege();
		StringBuilder sb = new StringBuilder();
		String str;
		for (int i = 0; i < list.size(); i++) {
			str = list.get(i);
			if(i != 0) sb.append("、");
			sb.append(str);
		}
		mav.addObject("collegeB", sb.toString());
		return mav;
	}
	
	/**
	 * 通过学年及学期获取学院信息及当前状态
	 * @param year
	 * @param semester
	 * @return
	 */
	@RequestMapping(value = "/get_teachers", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String getCollegeMessageByAdmin() {
		List<ScoreInputShow> list = null;
		int semester;
		semester = Integer.parseInt(someMessageMapper.getValueByName("semester"));
		if(semester == 1) {
			list = semester1Mapper.getScoreInputShow();
		} else if(semester == 2) {
			list = semester2Mapper.getScoreInputShow();
		}
		String str = MainUtil.getJsonToTable(list);
		return str;
	}
	
	/**
	 * 接受前台发来的对象数组字符串，转换为对象后，存储入数据库
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/score_input", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String scoreInput(@RequestBody  String data) {
		ObjectMapper objectMapper = new ObjectMapper();  
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, ScoreInput.class);  
        List<ScoreInput> list = null; 
        try {
			list = objectMapper.readValue(data, javaType);
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnMessageUtil.MESSAGE_WRONG;
		}
        if(list == null) return ReturnMessageUtil.SYSTEM_BUSY;
        //以下开始存储数据
        ScoreInput s;
        int semester;
        int year;
        int result;
        StringBuilder sb = new StringBuilder(); //用于储存未能正确保存的行数;
        try {                 
			semester = Integer.parseInt(someMessageMapper.getValueByName("semester"));
			year = Integer.parseInt(someMessageMapper.getValueByName("year"));
		} catch (NumberFormatException e) {   //若非数字，不能储存，返回错误信息
			e.printStackTrace();
			return ReturnMessageUtil.SOME_MESSAGE_NUN;
		}
		for (int i = 0; i < list.size(); i++) {
			s = list.get(i);
			if(s.getJobNumber()== null || s.getStudentScore() == null || s.getJobNumber() == "" || s.getStudentScore() == "") { //若数据有为空，则记录出错行数，跳过此次循环
				sb.append("第" + (i + 2) + "行数据为空<br/>");
				continue;
			}
			if(!MainUtil.isDouble(s.getStudentScore())) {  //若数据不是double类型，则记录出错行数，跳过此次循环
				sb.append("第" + (i + 2) + "行数据非数字<br/>");
				continue;
			}
			Integer result1 = teacherDataMapper.checkJobNumber(s.getJobNumber());
			if(result1 == null || result1 == 0) {
				sb.append("第" + (i + 2) + "行职工号不存在<br/>");
				continue;
			}
			Integer collegeId = collegeMapper.getCollegeIdByCollegeName(s.getCollegeName());
			if(collegeId == null) {
				sb.append("第" + (i + 2) + "行学院名称不存在<br/>");
				continue;
			}
			s.setCollegeId(collegeId);
			s.setYear(year);
			if(semester == 1) {
				result = semester1Mapper.setStudentScore(s); //若result为0，则说明该职工号不存在，则插入一行新数据
				if(result == 0) {
					semester1Mapper.addStudentScore(s); 
				}
			} else if(semester == 2) {
				result = semester2Mapper.setStudentScore(s);
				if(result == 0) {
					semester2Mapper.addStudentScore(s);
				}
			}
		}
        String error = sb.toString(); //获取出错信息，并返回
        if(!error.equals("")) {
        	return error;
        }
		return ReturnMessageUtil.TRUE;
	}
	
	/**
	 * 修改单个教师的学生评教成绩
	 * @param scoreInput
	 * @return
	 */
	@RequestMapping(value = "/save_student_score", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String saveStudentScore(@RequestParam String jobNumber, @RequestParam Double studentScore) {
		ScoreInput scoreInput = new ScoreInput();
		scoreInput.setJobNumber(jobNumber);
		scoreInput.setStudentScore(studentScore.toString());
		int year = Integer.parseInt(someMessageMapper.getValueByName("year"));
		int semester = Integer.parseInt(someMessageMapper.getValueByName("semester"));
		scoreInput.setYear(year);
		int result = 0;
		if(semester == 1) {
			result = semester1Mapper.setStudentScore(scoreInput);
		} else if(semester == 2) {
			result = semester2Mapper.setStudentScore(scoreInput);
		}
		if(result == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
	}
}
