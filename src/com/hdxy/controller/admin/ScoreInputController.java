package com.hdxy.controller.admin;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdxy.mapper.CollegeMapper;
import com.hdxy.mapper.Semester1Mapper;
import com.hdxy.mapper.Semester2Mapper;
import com.hdxy.mapper.SomeMessageMapper;
import com.hdxy.pojo.College;
import com.hdxy.pojo.ScoreInput;
import com.hdxy.util.MainUtil;
import com.hdxy.util.ReturnMessageUtil;

@Controller
@RequestMapping("/admin")
public class ScoreInputController {
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	@Autowired
	private SomeMessageMapper someMessageMapper;
	
	@Autowired
	private Semester1Mapper semester1Mapper;
	
	@Autowired
	private Semester2Mapper semester2Mapper;

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
	public String getCollegeMessageByAdmin() {
		List<College> list = collegeMapper.getCollegesAndState();
		String str = MainUtil.getJsonToTable(list);
		return str;
	}
	
	/**
	 * 接受前台发来的对象数组字符串，转换为对象后，存储入数据库
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/score_input", method = RequestMethod.POST)
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
			if(s.getJobNumber() == "" || s.getStudentScore() == "") { //若数据有为空，则记录出错行数，跳过此次循环
				sb.append("第" + i + 1 + "行数据为空<br />");
				continue;
			}
			if(!MainUtil.isDouble(s.getStudentScore())) {  //若数据不是double类型，则记录出错行数，跳过此次循环
				sb.append("第" + i + 1 + "行评教成绩非数字形式<br />");
				continue;
			}
			if(semester == 1) {
				result = semester1Mapper.setStudentScore(s); //若result为0，则说明该职工号不存在，则插入一行新数据
				if(result == 0) {
					s.setYear(year);
					semester1Mapper.addStudentScore(s);
				}
			} else if(semester == 2) {
				result = semester2Mapper.setStudentScore(s);
				if(result == 0) {
					s.setYear(year);
					semester2Mapper.addStudentScore(s);
				}
			}
		}
        String error = sb.toString(); //获取出错信息，并返回
        if(!error.equals("")) return error;
		return ReturnMessageUtil.TRUE;
	}
}
