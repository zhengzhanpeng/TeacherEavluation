package com.hdxy.mapper;

import org.apache.ibatis.annotations.Param;

import com.hdxy.pojo.Admin;

public interface AdminMapper {
	
	/*通过管理员用户名获取管理员id、password、random*/
	Admin getAdminByAdminName(String adminName);

	Admin getAdminByAdminId(Integer adminId);
	
	int addAdmin(Admin admin);
	
	int setPassword(@Param("adminId") int adminId, @Param("password") String password);
}
