package com.hdxy.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hdxy.mapper.CollegeMapper;
import com.hdxy.pojo.College;
import com.hdxy.util.MainUtil;

@Controller
public class CollegeController {
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	/**
	 * 添加一个学院至数据库
	 * @param collegeName
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/add_college", method = RequestMethod.POST)
	@ResponseBody
	public String addCollege(@RequestParam String collegeName, @RequestParam String phone) {
		if(collegeName == null) {
			return "学院名称不能为空";
		}
		College college = new College();
		college.setCollegeName(collegeName);
		college.setPhone(phone);
		collegeMapper.addCollege(college);
		return "1";
	}
	
	/**
	 * 管理员访问各学院信息页面
	 * @return
	 */
//	@RequestMapping(value = "/college_message", method = RequestMethod.GET)
//	public ModelAndView toCollegeMessage() {
//		ModelAndView modelAndView = new ModelAndView("college_message");
//		return modelAndView;
//	}
	
	@RequestMapping(value = "/get_college_by_admin", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	public String getCollegeMessageByAdmin() {
		List<College> list = collegeMapper.getColleges();
		return "INDEX";
	}
}
