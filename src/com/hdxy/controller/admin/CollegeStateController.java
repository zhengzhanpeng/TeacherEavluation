package com.hdxy.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdxy.mapper.CollegeMapper;
import com.hdxy.util.ReturnMessageUtil;

@Controller
@RequestMapping("/admin")
public class CollegeStateController {
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	/**
	 * 跳转至学院状态页面
	 * @return
	 */
	@RequestMapping(value = "/college_state", method = RequestMethod.GET)
	public String getCollegeState() {
		return "admin/college_state";
	}
	
	/**
	 * 打开所有学院状态
	 * @return
	 */
	@RequestMapping(value = "/open_all_college_state", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String openAllCollegeState() {
		int result = collegeMapper.openAllCollegeState();
		if(result == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
	}
	
	/**
	 * 开启指定学院状态
	 * @param collegeName
	 * @return
	 */
	@RequestMapping(value = "/open_college_state", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String openCollegeState(@RequestParam String collegeName) {
		Integer collegeId = collegeMapper.getCollegeIdByCollegeName(collegeName);
		if(collegeId == null) return ReturnMessageUtil.COLLEGE_NAME_NOT_EXIST;
		int result = collegeMapper.setState(collegeId, 1);
		if(result == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
	}
	
	
}
