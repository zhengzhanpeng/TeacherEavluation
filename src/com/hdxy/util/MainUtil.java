package com.hdxy.util;

import java.util.List;


import net.sf.json.JSONArray;

public class MainUtil {
	
	public static String getJsonToTable(@SuppressWarnings("rawtypes") List list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		String str =jsonArray.toString();
		StringBuilder sb = new StringBuilder();
		sb.append("{\"data\":");
		sb.append(str);
		sb.append("}");
		return sb.toString();
	}
	
	public static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}







