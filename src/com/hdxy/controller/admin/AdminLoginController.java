package com.hdxy.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdxy.mapper.AdminMapper;
import com.hdxy.pojo.Admin;
import com.hdxy.util.EncryptionUtil;
import com.hdxy.util.ReturnMessageUtil;

@Controller
@RequestMapping("/admin")
@SessionAttributes(value = {"adminId"}, types = {Integer.class})
public class AdminLoginController {
	
	@Autowired
	private AdminMapper adminMapper;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String adminLoginGet() {
		return "admin/login";
	}
	
	/*验证管理员的密码是否正确，如果正确return1，否则return0*/
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String adminLoginPost(@RequestParam String adminName
							,@RequestParam String password
							,ModelMap model) {
		if(adminName == null || password == null || adminName == "" || password == "") {
			return ReturnMessageUtil.MESSAGE_IS_NULL;
		}
		Admin admin = adminMapper.getAdminByAdminName(adminName);
		if(admin == null) return ReturnMessageUtil.USER_NOT_EXIST;
		password = EncryptionUtil.getPassword(password, admin.getRandom(), "MD5");
		if(password.equals(admin.getPassword())) {
			model.addAttribute("adminId", admin.getId());
			return ReturnMessageUtil.TRUE;
		}
		return ReturnMessageUtil.PASSOWRD_WRONG;
	}
}
