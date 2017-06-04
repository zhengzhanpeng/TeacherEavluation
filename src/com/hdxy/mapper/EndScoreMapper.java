package com.hdxy.mapper;

import java.util.List;

import com.hdxy.pojo.EndScore;

public interface EndScoreMapper {
	
	/**
	 * 获取当前学年期末总成绩
	 * @param collegeId
	 * @return
	 */
	List<EndScore> getEndScores(int collegeId);
}
