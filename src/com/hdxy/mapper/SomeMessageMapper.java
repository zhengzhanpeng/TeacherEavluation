package com.hdxy.mapper;

import org.apache.ibatis.annotations.Param;

public interface SomeMessageMapper {
	String getValueByName(String name);
	int setValueByName(@Param("name") String name, @Param("value") String value);
}
