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
import com.hdxy.util.ReturnMessageUtil;

@Controller
@RequestMapping("/admin")
public class CollegeController {

	@Autowired
	private CollegeMapper collegeMapper;
	
	/**
	 * 转至各学院信息页面
	 * @return
	 */
	@RequestMapping(value = "/college_message", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	public String getCollege() {
		return "admin/college_message";
	}
	
	/**
	 * 添加一个学院至数据库
	 * @param collegeName
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/save_college", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String addCollege(@RequestParam String collegeName, @RequestParam String phone, @RequestParam String collegeId) {
		if(collegeName == "") {
			return ReturnMessageUtil.COLLEGE_NAME_IS_NULL;
		}
		College college = new College();
		college.setCollegeName(collegeName);
		college.setPhone(phone);
		if(collegeId == "") {
			collegeMapper.addCollege(college);
		} else {
			int id = Integer.parseInt(collegeId);
			college.setId(id);
			collegeMapper.updateCollege(college);
		}
		return ReturnMessageUtil.TRUE;
	}
	
	/**
	 * 删除指定学院
	 * @param collegeId
	 * @return
	 */
	@RequestMapping(value = "/delete_college", method = RequestMethod.POST)
	@ResponseBody
	public String deleteCollege(@RequestParam Integer collegeId) {
		int result = collegeMapper.deleteCollege(collegeId);
		if(result == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
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
