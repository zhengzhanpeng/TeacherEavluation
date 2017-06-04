package com.hdxy.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdxy.mapper.UserMapper;
import com.hdxy.pojo.User;
import com.hdxy.util.EncryptionUtil;

@Controller
@SessionAttributes(value = {"userId"}, types = {Integer.class})
public class UserController {
	
	@Autowired
	UserMapper userMapper;
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param password
	 * @param newPassword1
	 * @param newPassword2
	 * @return 除返回字符串“1”外，其余都返回为错误信息
	 */
	@RequestMapping(value = "/user_set_password", method = RequestMethod.POST)
	@ResponseBody
	public String setPassword(@ModelAttribute("userId") Integer userId
			    			 ,@RequestParam String password
			    			 ,@RequestParam String newPassword1
			    			 ,@RequestParam String newPassword2) {
		if(!newPassword1.equals(newPassword2)) return "您两次输入的两次新密码不一致，请重新输入";
		User user = userMapper.getUserByUserId(userId);
		password = EncryptionUtil.getPassword(password, user.getRandom(), "MD5");
		if(!password.equals(user.getPassword())) return "对不起，您输入的密码不正确";
		newPassword1 = EncryptionUtil.getPassword(newPassword1, user.getRandom(), "MD5");
		return "1";
	}
	
	/**
	 * 用于添加用户的方法，一般由管理员调用
	 * @param user
	 * @param userId
	 * @return 如果用户名已存在return 0，成功添加用户return 1, 添加信息为空return 2
	 */
	@RequestMapping(value = "add_user", method = RequestMethod.POST)
	@ResponseBody
	public String addUser(@ModelAttribute User user, @ModelAttribute("userId") Integer userId) {
		if(user.getUserName() == null) {
			return "用户名不能为空";
		} else if(user.getPassword() == null) {
			return "密码不能为空";
		} else if(user.getCollegeId() == 0) {
			return "非法操作，请重试";
		}
		int result = userMapper.checkUserName(user.getUserName());
		if(result > 0) return "对不起,该用户名已存在";
		user.setRandom(EncryptionUtil.getRandom());
		String password = user.getPassword();
		password = EncryptionUtil.getPassword(password, user.getRandom(), "MD5");
		user.setPassword(password);
		userMapper.addUser(user);
		return "1";
	}
	
	/**
	 * 通过用户Id删除指定用户
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/delete_user", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	public String deleteUser(@RequestParam int userId) {
		int result = userMapper.deleteUserByUserId(userId);
		if(result == 0) return "该用户不存在";
		return "1";
	}
	
	@RequestMapping(value = "/user_index", method = RequestMethod.GET)
	public String getUserIndex() {
		return "user/index";
	}
	
	
}
