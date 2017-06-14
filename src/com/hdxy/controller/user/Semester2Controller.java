package com.hdxy.controller.user;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdxy.mapper.CollegeMapper;
import com.hdxy.mapper.Semester2Mapper;
import com.hdxy.mapper.SomeMessageMapper;
import com.hdxy.mapper.TeacherDataMapper;
import com.hdxy.mapper.UserMapper;
import com.hdxy.pojo.Semester2;
import com.hdxy.util.MainUtil;
import com.hdxy.util.ReturnMessageUtil;

@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"userId", "collegeId"}, types = {Integer.class})
public class Semester2Controller {
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	@Autowired
	private SomeMessageMapper someMessageMapper;
	
	@Autowired
	private TeacherDataMapper teacherDataMapper;
	
	@Autowired
	private Semester2Mapper semester2Mapper;
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 首先判断是否相应状态已打开,如果没有打开跳转到主页面
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/semester2", method = RequestMethod.GET)
	public String toSemester2(@ModelAttribute("collegeId") Integer collegeId) {
		Integer state = collegeMapper.getState(collegeId);
		String semester = someMessageMapper.getValueByName("semester");
		if(state == null || state != 1 || !semester.equals("2")) return "user/main";
		return "user/semester2";
	}
	
	/**
	 * 获取教师已录入的学院成绩
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/get_semester2s", method = RequestMethod.POST)
	@ResponseBody
	public String getSemester2s(@ModelAttribute("userId") Integer userId) {
		List<Semester2> list = semester2Mapper.getSemester2s(userMapper.getCollegeIdByUserId(userId));
		String str = MainUtil.getJsonToTable(list);
		return str;
	}
	
	/**
	 * 录入第一学期单个教师部分成绩，其中自动录入的信息为学年和录入时间
	 * 内置判断信息不能为空，和验证职工号是否存在
	 * @param list
	 * @return 返回为提示的字符串，如果信息无误且录入成功则返回1
	 */
	@RequestMapping(value = "/add_semester2", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addSemester2(@ModelAttribute Semester2 semester2, @ModelAttribute("userId") Integer userId) {
		if(semester2.getJobNumber() == "" || semester2.getName() == "" || semester2.getPeerScore() == 0 || semester2.getSuperviseScore() == 0) {
			return ReturnMessageUtil.MESSAGE_IS_NULL;
		}
		if(semester2.getPeerScore() > 100 || semester2.getSuperviseScore() > 100 || semester2.getTeachScore() > 100) {
			return ReturnMessageUtil.SCORE_IS_BIGER;
		}
		int year = Integer.parseInt(someMessageMapper.getValueByName("year"));
		Integer reslut = teacherDataMapper.checkJobNumber(semester2.getJobNumber());
		if(reslut == null) return ReturnMessageUtil.JOB_NUMMBER_NOT_EXIST;
		semester2.setDate(new Date());
		semester2.setYear(year);
		semester2.setCollegeId(userMapper.getCollegeIdByUserId(userId));
		Integer reslut2 = semester2Mapper.checkSemester2(semester2.getJobNumber(), semester2.getYear());
		if(reslut2 != null) {
			semester2Mapper.updateSemester2(semester2);
		} else {
			semester2Mapper.addSemester2(semester2);
		}
		return ReturnMessageUtil.TRUE;
	}
	
	/**
	 * 根据教师职工号和当前年份删除相应的semester记录
	 * @param jobNumber
	 * @return 0 则表示删除内容不存在 ； 1 删除成功
	 */
	@RequestMapping(value = "/delete_semester2", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String deleteSemester2(@RequestParam String jobNumber) {
		String str = someMessageMapper.getValueByName("year");
		int year = Integer.parseInt(str);
		int value = semester2Mapper.deleteSemester2(jobNumber, year);
		if(value == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
	}
	
	/**
	 * 学院提交录入的教师成绩，collegeState中的state 变为2；
	 * @param userId
	 * @return 若提交失败则返回 0； 成功返回 1；
	 */
	@RequestMapping(value = "/submit_semester2", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String submitSemester2(@ModelAttribute("collegeId") int collegeId) {
		int value = collegeMapper.setState(collegeId, 2);
		if(value == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
	}
}
