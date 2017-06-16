package com.hdxy.mapper;

import org.apache.ibatis.annotations.Param;

import com.hdxy.pojo.TeacherData;

public interface TeacherDataMapper {
	Integer checkJobNumber(String jobNumber);
	int updateTeacher(TeacherData teacherData);
	int addTeacher(TeacherData teacherData);
	Integer checkTeacher(@Param("jobNumber") String jobNumber, @Param("name") String name);
}
