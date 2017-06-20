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
import com.hdxy.mapper.UserMapper;
import com.hdxy.pojo.College;
import com.hdxy.pojo.User;
import com.hdxy.pojo.UserMessage;
import com.hdxy.util.EncryptionUtil;
import com.hdxy.util.MainUtil;
import com.hdxy.util.ReturnMessageUtil;

@Controller
@RequestMapping("/admin")
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	/**
	 * 返回用户信息页面
	 * @return
	 */
	@RequestMapping(value = "/user_message", method = RequestMethod.GET)
	public String getUserMessage() {
		return "admin/user_message";
	}
	
	/**
	 * 获取所有用户信息，返回值包括学院名称、用户名、电话。
	 * @return
	 */
	@RequestMapping(value = "/get_users", method = RequestMethod.POST)
	@ResponseBody
	public String getUsers() {
		List<UserMessage> list = userMapper.getUsers();
		String str = MainUtil.getJsonToTable(list);
		return str;
	}
	
	/**
	 * 通过用户Id删除指定用户
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/delete_user", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUser(@RequestParam String userName) {
		int result = userMapper.deleteUserByUserName(userName);
		if(result == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
	}
	
	/**
	 * 添加用户，根据学院名称获取学院Id
	 * @param userName
	 * @param password
	 * @param collegeName
	 * @return
	 */
	@RequestMapping(value = "/add_user", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String addUser(@RequestParam String userName, @RequestParam String password
			, @RequestParam Integer collegeId) {
		if(userName == "" || password == "" || collegeId == null) {
			return ReturnMessageUtil.MESSAGE_IS_NULL;
		}
		User user = new User();
		user.setCollegeId(collegeId);
		user.setUserName(userName);
		String random = EncryptionUtil.getRandom();
		user.setRandom(random);
		password = EncryptionUtil.getPassword(password, random, "MD5");
		user.setPassword(password);
		Integer result = userMapper.checkUserName(userName);
		if(result != null) return ReturnMessageUtil.USER_IS_EXIST;	
		result = userMapper.addUser(user);
		if(result == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		College college = collegeMapper.getCollegeByCollegeId(collegeId);
		StringBuilder sb = new StringBuilder();
		sb.append("1-").append(college.getPhone()).append("-").append(college.getCollegeName());
		return sb.toString();
	}
	
	/**
	 * 通过用户Id修改指定用户密码
	 * @param userId
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/set_user_password", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String setUserPassword(@RequestParam String userName, @RequestParam String password) {
		User user = userMapper.getUserByUserName(userName);
		password = EncryptionUtil.getPassword(password, user.getRandom(), "MD5");
		int result = userMapper.setUserPasswordByUserName(userName, password);
		if(result == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
	}
	
	@RequestMapping(value = "add_user_model")
	public ModelAndView getUserMessageModel() {
		ModelAndView mav = new ModelAndView("admin/add_user_model");
		List<College> list = collegeMapper.getColleges();
		mav.addObject("list", list);
		return mav;
	}
}









