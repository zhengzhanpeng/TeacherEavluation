package com.hdxy.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@SessionAttributes(value = {"userId", "collegeId"}, types = {Integer.class})
public class UserLoginController {
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String userLoginGet() {
		return "user/login";
	}
	
	/*验证教师的密码是否正确，如果正确return1，否则return0*/
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String userLoginPost(@RequestParam String userName
							,@RequestParam String password
							,ModelMap model) {
		if(userName == null || userName.equals("")) {
			return ReturnMessageUtil.USER_NAME_IS_NULL;
		} else if(password == null || password.equals("")) {
			return ReturnMessageUtil.PASSWORD_IS_NULL;
		}
		User user = userMapper.getUserByUserName(userName);
		if(user == null) {
			return ReturnMessageUtil.USER_NOT_EXIST;
		}
		password = EncryptionUtil.getPassword(password, user.getRandom(), "MD5");
		if(password.equals(user.getPassword())) {
			model.addAttribute("userId", user.getId());
			model.addAttribute("collegeId", user.getCollegeId());
			return ReturnMessageUtil.TRUE; 
		}
		return ReturnMessageUtil.USER_NAME_OR_PASSWORD_WRONG;
	}
}
