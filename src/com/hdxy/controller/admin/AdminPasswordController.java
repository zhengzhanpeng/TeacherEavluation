package com.hdxy.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class AdminPasswordController {
	
	@Autowired
	private AdminMapper adminMapper;
	
	/**
	 * 修改管理员密码
	 * @param adminId
	 * @param password
	 * @param newPassword1
	 * @param newPassword2
	 * @return 除返回字符串“1”外，其余都返回为错误信息
	 */
	@RequestMapping(value = "/set_password", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String setPassword(@ModelAttribute("adminId") Integer adminId
			    			 ,@RequestParam String password
			    			 ,@RequestParam String newPassword1
			    			 ,@RequestParam String newPassword2) {
		if(!newPassword1.equals(newPassword2)) return ReturnMessageUtil.PASSWORD_DIFFERENT;
		Admin admin = adminMapper.getAdminByAdminId(adminId);
		password = EncryptionUtil.getPassword(password, admin.getRandom(), "MD5");
		if(!password.equals(admin.getPassword())) return ReturnMessageUtil.PASSOWRD_WRONG;
		newPassword1 = EncryptionUtil.getPassword(newPassword1, admin.getRandom(), "MD5");
		return ReturnMessageUtil.TRUE;
	}
}
