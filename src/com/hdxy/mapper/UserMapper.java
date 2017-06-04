package com.hdxy.mapper;

import com.hdxy.pojo.User;

public interface UserMapper {
	User getUserByUserName(String userName);
	int checkUserName(String userName);
	void addUser(User user);
	int getCollegeIdByUserId(int userId);
	
	/**
	 * 通过userId获取用户的密码和加密盐
	 * @param userId
	 * @return
	 */
	User getUserByUserId(int userId);
	
	int deleteUserByUserId(int userId);
}
