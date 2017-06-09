package com.hdxy.mapper;

import org.apache.ibatis.annotations.Param;

public interface FormulaMapper {
	
	/**
	 * 通过列名和计算结果名获取对应数值
	 * @param name
	 * @param resultName
	 * @return
	 */
	Double getValue(@Param("name") String name, @Param("resultName") String resultName);
	
	/**
	 * 通过列名和计算结果名修改对应数值
	 * @param name
	 * @param resultName
	 * @return
	 */
	Integer setValue(@Param("name") String name, @Param("resultName") String resultName, @Param("value") double value);
}
