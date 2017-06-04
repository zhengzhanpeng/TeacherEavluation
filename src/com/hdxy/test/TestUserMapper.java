package com.hdxy.test;

import java.util.Date;
import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.hdxy.mapper.Semester1Mapper;
import com.hdxy.mapper.SomeMessageMapper;
import com.hdxy.mapper.UserMapper;
import com.hdxy.pojo.Semester1;
import com.hdxy.pojo.User;
import com.hdxy.util.EncryptionUtil;

@Service
public class TestUserMapper {
	
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		
//		UserMapper userMapper = (UserMapper) context.getBean("userMapper");
//		User user = new User();
//		user.setPassword("user");
//		user.setCollegeId(1);
//		user.setRandom(EncryptionUtil.getRandom());
//		System.out.println(user.getRandom().length());
//		user.setPassword(EncryptionUtil.getPassword(user.getPassword(), user.getRandom(), "MD5"));
//		user.setUserName("user1");
//		userMapper.addUser(user);
		
//		CollegeStateMapper collegeStateMapper = (CollegeStateMapper) context.getBean("collegeStateMapper");
//		int state = collegeStateMapper.getStateByUserId(1);
//		System.out.println(state);
		
//		SomeMessageMapper someMessageMapper = (SomeMessageMapper) context.getBean("someMessageMapper");
//		String year = someMessageMapper.getValueByName("year");
//		System.out.println(year);
		
//		Semester1Mapper semester1Mapper = (Semester1Mapper) context.getBean("semester1Mapper");
//		Semester1 semester1 = new Semester1();
//		semester1.setDate(new Date());
//		semester1.setCollegeId(1);
//		semester1.setEndScore(1);
//		semester1.setJobNumber("40");
//		semester1.setName("333");
//		semester1.setPeerScore(1);
//		semester1.setYear(2018);
//		semester1.setSuperviseScore(1.2233);
//		semester1Mapper.addSemester1(semester1);
//		Integer result = semester1Mapper.checkSemester1("4000", 2017);
//		if(result == null) System.out.println(11);
//		System.out.println(result);
//		semester1Mapper.updateSemester1(semester1);
//		List<Semester1> list = semester1Mapper.getSemester1s(1);
	}
}
