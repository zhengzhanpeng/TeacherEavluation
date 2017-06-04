package com.hdxy.util;

import java.util.List;

import com.hdxy.pojo.Semester1;
import com.hdxy.pojo.Semester2;

import net.sf.json.JSONArray;

public class MainUtil {
	
	public static String getJsonToTable(List list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		String str =jsonArray.toString();
		StringBuilder sb = new StringBuilder();
		sb.append("{\"data\":");
		sb.append(str);
		sb.append("}");
		return sb.toString();
	}
}
