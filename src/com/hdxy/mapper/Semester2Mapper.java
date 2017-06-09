package com.hdxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdxy.pojo.ScoreInput;
import com.hdxy.pojo.ScoreInputShow;
import com.hdxy.pojo.Semester2;

public interface Semester2Mapper {
	
	/**
	 * 通过教师职工号、所查学年检验是否存在对应数据
	 * @param jobNumber
	 * @param year
	 * @return 
	 */
	Integer checkSemester2(@Param("jobNumber") String jobNumber, @Param("year") int year);
	
	/**
	 * 添加学院录入的教师成绩
	 * @param semester1
	 */
	void addSemester2(Semester2 semester2);
	
	/**
	 * 根据职工号更新学院录入的教师成绩
	 * @param semester1
	 */
	void updateSemester2(Semester2 semester2);
	
	/**
	 * 根据教师职工号、相应学年删除对应的录入成绩
	 * @param jobNumber
	 * @param year
	 * @return
	 */
	int deleteSemester2(@Param("jobNumber") String jobNumber, @Param("year") int year);

	/**
	 * 根据collegeId获取相应学院录入的成绩信息，其中year在sql语句中select
	 * @param collegeId
	 * @return
	 */
	List<Semester2> getSemester2s(int collegeIdByUserId);
	
	/**
	 * 通过职工号和学年录入学生评教成绩
	 * @param jobNumber
	 * @return
	 */
	int setStudentScore(ScoreInput scoreInput);
	
	/**
	 * 通过职工号和学年插入学生评教成绩，
	 * 以及从teacherData表中读取教师姓名、职称信息一并插入
	 * @param scoreInput
	 * @return
	 */
	int addStudentScore(ScoreInput scoreInput);
	
	/**
	 * 获取录入学生评教页面要展示的信息
	 * @return
	 */
	List<ScoreInputShow> getScoreInputShow();
}
