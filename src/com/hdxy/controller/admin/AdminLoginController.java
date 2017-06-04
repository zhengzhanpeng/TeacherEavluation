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
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public int adminLoginPost(@RequestParam String adminName
							,@RequestParam String password
							,ModelMap model) {
		Admin admin = adminMapper.getAdminByAdminName(adminName);
		password = EncryptionUtil.getPassword(adminName, admin.getRandom(), "MD5");
		if(password.equals(admin.getPassword())) {
			model.addAttribute("adminId", admin.getId());
			return 1;
		}
		return 0;
	}
}
