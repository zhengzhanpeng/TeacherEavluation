package com.hdxy.mapper;

import java.util.List;

import com.hdxy.pojo.College;

public interface CollegeMapper {
	
	/**
	 * 获取所有的学院的ID、名称以及联系电话
	 * @return
	 */
	List<College> getColleges();
	
	/**
	 * 添加学院
	 * @param college
	 */
	void addCollege(College college);
	
	/**
	 * 删除学院
	 * @param id
	 * @return
	 */
	Integer deleteCollege(int id);
	
	/**
	 * 根据学院ID更新数据，更新的数据仅有学院名称、学院电话
	 * @param collegeId
	 * @return
	 */
	int updateCollege(College college);
	
	/**
	 * 获得个学院信息和对应的状态
	 * @return
	 */
	List<College> getCollegesAndState();
	
	/**
	 * 通过用户ID、所查学年、所查学期获取相应状态
	 * @param userId
	 * @param year
	 * @param semester
	 * @return 若返回1则为待提交状态
	 */
	Integer getState(int collegeId);
	
	/**
	 * 通过学院ID、相应学期、相应学年修改对应的状态
	 * @param collegeState
	 * @return 返回修改的数目
	 */
	Integer setState(int collegeId);
	
	/**
	 * 通过collegeName获取CollegeId
	 * @param collegeName
	 * @return
	 */
	Integer getCollegeIdByCollegeName(String collegeName);
}
