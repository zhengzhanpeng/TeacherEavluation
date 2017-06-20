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
	
	public static String getJsonToTable2(@SuppressWarnings("rawtypes") List list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		String str =jsonArray.toString();
		StringBuilder sb = new StringBuilder();
		sb.append("{\"data2\":");
		sb.append(str);
		sb.append("}");
		return sb.toString();
	}
	
	public static String getJsonToTable3(@SuppressWarnings("rawtypes") List list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		String str =jsonArray.toString();
		StringBuilder sb = new StringBuilder();
		sb.append("{\"data3\":");
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







