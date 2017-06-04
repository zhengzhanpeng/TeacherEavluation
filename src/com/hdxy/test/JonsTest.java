package com.hdxy.test;

import java.util.ArrayList;
import java.util.List;

import com.hdxy.pojo.Semester1;
import com.hdxy.pojo.User;

import net.sf.json.JSONArray;

public class JonsTest {
	public static void main(String[] args) {
		List<Semester1> list = new ArrayList<Semester1>();
        //创建json集合
		Semester1 semester1 = new Semester1();
		list.add(semester1);
        JSONArray jsonArray = JSONArray.fromObject(list);
        System.out.println(jsonArray.toString());
	}
}
