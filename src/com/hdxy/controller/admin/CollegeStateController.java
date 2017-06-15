package com.hdxy.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hdxy.mapper.CollegeMapper;
import com.hdxy.mapper.SomeMessageMapper;
import com.hdxy.pojo.CollegeState;
import com.hdxy.util.MainUtil;
import com.hdxy.util.ReturnMessageUtil;

@Controller
@RequestMapping("/admin")
public class CollegeStateController {
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	@Autowired
	private SomeMessageMapper someMessageMapper;
	
	/**
	 * 跳转至学院状态页面
	 * @return
	 */
	@RequestMapping(value = "/college_state", method = RequestMethod.GET)
	public ModelAndView getCollegeState() {
		ModelAndView mav = new ModelAndView("admin/college_state");
		mav.addObject("year", someMessageMapper.getValueByName("year"));
		mav.addObject("semester", someMessageMapper.getValueByName("semester"));
		return mav;
	}
	
	@RequestMapping(value = "/get_states", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String getStates() {
		List<CollegeState> list = collegeMapper.getCollegesAndState();
		String str = MainUtil.getJsonToTable(list);
		return str;
	}
	
	/**
	 * 打开所有学院状态
	 * @return
	 */
	@RequestMapping(value = "/open_all_college_state", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String openAllCollegeState() {
		int result = collegeMapper.setAllCollegeState(1);
		if(result == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
	}
	
	/**
	 * 关闭所有学院状态
	 * @return
	 */
	@RequestMapping(value = "/close_all_college_state", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String closeAllCollegeState() {
		int result = collegeMapper.setAllCollegeState(-1);
		if(result == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
	}
	
	/**
	 * 开启指定学院状态
	 * @param collegeId
	 * @return
	 */
	@RequestMapping(value = "/open_college_state", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String openCollegeState(@RequestParam Integer collegeId) {
		if(collegeId == null) return ReturnMessageUtil.SYSTEM_BUSY;
		int result = collegeMapper.setState(collegeId, 1);
		if(result == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
	}
	
	/**
	 * 关闭指定学院状态
	 * @param collegeId
	 * @return
	 */
	@RequestMapping(value = "/close_college_state", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String closeCollegeState(@RequestParam Integer collegeId) {
		if(collegeId == null) return ReturnMessageUtil.SYSTEM_BUSY;
		int result = collegeMapper.setState(collegeId, -1);
		if(result == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
	}
	
	/**
	 * 修改当前学期跟学年，修改后所有显示的都以此为准
	 * @param year
	 * @param semester
	 * @return
	 */
	@RequestMapping(value = "/set_semester", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String setSemester(@RequestParam String year, @RequestParam String semester) {
		if(year == "" || semester == "") return ReturnMessageUtil.MESSAGE_IS_NULL;
		try {
			@SuppressWarnings("unused")
			int yearNum = Integer.parseInt(year);
			int semeser = Integer.parseInt(semester);
			if(semeser != 1 && semeser != 2) return ReturnMessageUtil.SEMESTER_WRONG;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ReturnMessageUtil.MESSAGE_NOT_NUMBER;
		}
		int result1 = someMessageMapper.setValueByName("year", year);
		int result2 = someMessageMapper.setValueByName("semester", semester);
		if(result1 == 0 || result2 == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
	}
}
