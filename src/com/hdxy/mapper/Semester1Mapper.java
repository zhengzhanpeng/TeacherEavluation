package com.hdxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdxy.pojo.Semester1;

public interface Semester1Mapper {
	
	/**
	 * 通过教师职工号、所查学年获取相应学期的提交状态
	 * @param jobNumber
	 * @param year
	 * @return 若为待录入教师成绩则返回1， 否则返回0
	 */
	Integer checkSemester1(@Param("jobNumber") String jobNumber, @Param("year") int year);
	
	/**
	 * 添加学院录入的教师成绩
	 * @param semester1
	 */
	void addSemester1(Semester1 semester1);
	
	/**
	 * 根据职工号更新学院录入的教师成绩
	 * @param semester1
	 */
	void updateSemester1(Semester1 semester1);
	
	/**
	 * 根据教师职工号、相应学年删除对应的录入成绩
	 * @param jobNumber
	 * @param year
	 * @return
	 */
	int deleteSemester1(@Param("jobNumber") String jobNumber, @Param("year") int year);
	
	/**
	 * 根据collegeId获取相应学院录入的成绩信息，其中year在sql语句中select
	 * @param collegeId
	 * @return
	 */
	List<Semester1> getSemester1s(@Param("collegeId") int collegeId);
}







