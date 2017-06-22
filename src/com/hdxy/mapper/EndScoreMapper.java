package com.hdxy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdxy.pojo.EndScore;
import com.hdxy.pojo.ScoreToCompute;

public interface EndScoreMapper {
	
	/**
	 * 获取当前学年期末总成绩
	 * @param collegeId
	 * @return
	 */
	List<EndScore> getEndScores(int collegeId);
	
	/**
	 * 获取当前学年期末总成绩
	 * @param collegeId
	 * @return
	 */
	List<EndScore> getEndScoresToIndex(@Param("collegeId") int collegeId, @Param("number") int number);
	
	/**
	 * 根据当前学年获取第一学期、第二学期期末成绩、还有教学评教成绩
	 * @param year
	 * @return
	 */
	List<ScoreToCompute> geScoreToCompute(int year);
	
	/**
	 * 根据stc的值计算出期末成绩对应的值，并获取对应教师信息
	 * @param stc
	 * @return
	 */
	int addEndScore(ScoreToCompute stc);
	
	int setEndScore(ScoreToCompute stc);
}
