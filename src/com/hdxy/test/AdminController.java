package com.hdxy.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdxy.mapper.AdminMapper;
import com.hdxy.mapper.CollegeMapper;
import com.hdxy.mapper.UserMapper;
import com.hdxy.pojo.Admin;
import com.hdxy.pojo.College;
import com.hdxy.pojo.User;
import com.hdxy.util.EncryptionUtil;

@Controller
@SessionAttributes(value = {"adminId"}, types={Integer.class})
public class AdminController {
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	/**
	 * 修改管理员密码
	 * @param adminId
	 * @param password
	 * @param newPassword1
	 * @param newPassword2
	 * @return 除返回字符串“1”外，其余都返回为错误信息
	 */
	@RequestMapping(value = "/admin_set_password", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String setPassword(@ModelAttribute("adminId") Integer adminId
			    			 ,@RequestParam String password
			    			 ,@RequestParam String newPassword1
			    			 ,@RequestParam String newPassword2) {
		if(!newPassword1.equals(newPassword2)) return "您两次输入的两次新密码不一致，请重新输入";
		Admin admin = adminMapper.getAdminByAdminId(adminId);
		password = EncryptionUtil.getPassword(password, admin.getRandom(), "MD5");
		if(!password.equals(admin.getPassword())) return "对不起，您输入的密码不正确";
		newPassword1 = EncryptionUtil.getPassword(newPassword1, admin.getRandom(), "MD5");
		return "1";
	}
}
