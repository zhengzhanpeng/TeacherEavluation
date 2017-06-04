package com.hdxy.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdxy.mapper.UserMapper;
import com.hdxy.pojo.UserMessage;
import com.hdxy.util.MainUtil;
import com.hdxy.util.ReturnMessageUtil;

@Controller
@RequestMapping("/admin")
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
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
	
	@RequestMapping(value = "/delete_user", method = RequestMethod.POST)
	public String deleteUser(@RequestParam int userId) {
		int result = userMapper.deleteUserByUserId(userId);
		if(result == 0) return ReturnMessageUtil.SYSTEM_BUSY;
		return ReturnMessageUtil.TRUE;
	}
}
