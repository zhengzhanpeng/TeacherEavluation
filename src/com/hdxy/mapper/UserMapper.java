package com.hdxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdxy.pojo.User;
import com.hdxy.pojo.UserMessage;

public interface UserMapper {
	User getUserByUserName(String userName);
	Integer checkUserName(String userName);
	int addUser(User user);
	int getCollegeIdByUserId(int userId);
	
	/**
	 * 通过userId获取用户的密码和加密盐
	 * @param userId
	 * @return
	 */
	User getUserByUserId(int userId);
	
	int deleteUserByUserName(String userName);
	
	/**
	 * 获取用户的所有信息
	 * @return
	 */
	List<UserMessage> getUsers();
	
	int setUserPassword(@Param("userId") int userId, @Param("password") String password);
	
	int setUserPasswordByUserName(@Param("userName") String userName, @Param("password") String password);
}
