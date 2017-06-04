package com.hdxy.pojo;

public class College {
	private int id;
	private String collegeName;
	private String phone;
	/** -1: 录入状态未开启。1：打开了教师录入开关，教师可以录入信息。2：教师已提交录入成绩 3：学生评教成绩已导入 */
	private int state; 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}
