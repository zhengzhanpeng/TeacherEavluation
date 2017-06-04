package com.hdxy.controller.user;

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
import com.hdxy.util.ReturnMessageUtil;

@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"userId"}, types = {Integer.class})
public class UserPasswordController {
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param password
	 * @param newPassword1
	 * @param newPassword2
	 * @return 除返回字符串“1”外，其余都返回为错误信息
	 */
	@RequestMapping(value = "/set_password", method = RequestMethod.POST)
	@ResponseBody
	public String setPassword(@ModelAttribute("userId") Integer userId
			    			 ,@RequestParam String password
			    			 ,@RequestParam String newPassword1
			    			 ,@RequestParam String newPassword2) {
		if(!newPassword1.equals(newPassword2)) return ReturnMessageUtil.PASSWORD_DIFFERENT;
		User user = userMapper.getUserByUserId(userId);
		password = EncryptionUtil.getPassword(password, user.getRandom(), "MD5");
		if(!password.equals(user.getPassword())) return ReturnMessageUtil.PASSOWRD_WRONG;
		newPassword1 = EncryptionUtil.getPassword(newPassword1, user.getRandom(), "MD5");
		return ReturnMessageUtil.TRUE;
	}
}
