package com.hdxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdxy.pojo.EndScoreInput;
import com.hdxy.pojo.ScoreInput;
import com.hdxy.pojo.ScoreInputShow;
import com.hdxy.pojo.Semester1;
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
	 * 根据collegeId获取相应学院录入的成绩信息，其中year在sql语句中select
	 * @param collegeId
	 * @return
	 */
	List<Semester2> getSemester2sToIndex(@Param("collegeId") int collegeId, @Param("number") int number);
	
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

	/**
	 * 通过传入的计算值计算学期末成绩
	 * @param superviseScore
	 * @param peerScore
	 * @param studentScore
	 * @return
	 */
	int computeEndScore(@Param("superviseScore") double superviseScore
			, @Param("peerScore") double peerScore
			, @Param("studentScore") double studentScore
			, @Param("year") int year);
	
	/**
	 * 获取指定学年的期末成绩平均值
	 * 计算时不包括督导成绩和同行成绩为0的教师成绩
	 * @param year
	 * @return
	 */
	double getAvgEndScore(int year);
	
	/**
	 * 通过全联合查询，找出存在其中一个学期为空的信息
	 * @param collegeId
	 * @return
	 */
	List<EndScoreInput> getEndScoreInput(int collegeId);
	
	int addSemester2Set(Semester2 s);
	
	/**
	 * 查询需要补录的学院
	 * @return
	 */
	List<String> getEndScoreCollege();
	
	/**
	 * 更新教师期末成绩
	 */
	int updateEndScore(Semester2 semester2);
}
