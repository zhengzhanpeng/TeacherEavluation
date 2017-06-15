package com.hdxy.mapper;

import com.hdxy.pojo.TeacherData;

public interface TeacherDataMapper {
	Integer checkJobNumber(String jobNumber);
	int updateTeacher(TeacherData teacherData);
	int addTeacher(TeacherData teacherData);
}
