package com.hdxy.pojo;

import java.util.Date;

public class Semester2 {
	private int id;
	private String jobNumber;
	private String name;
	private String position;
	private Integer collegeId;
	private Double superviseScore;
	private Double peerScore;
	private Double studentScore;
	private Double teachScore;
	private Double endScore;
	private Date date;
	private Integer year;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}
	public Double getSuperviseScore() {
		return superviseScore;
	}
	public void setSuperviseScore(double superviseScore) {
		this.superviseScore = superviseScore;
	}
	public Double getPeerScore() {
		return peerScore;
	}
	public void setPeerScore(double peerScore) {
		this.peerScore = peerScore;
	}
	public Double getStudentScore() {
		return studentScore;
	}
	public void setStudentScore(double studentScore) {
		this.studentScore = studentScore;
	}
	public Double getTeachScore() {
		return teachScore;
	}
	public void setTeachScore(double teachScore) {
		this.teachScore = teachScore;
	}
	public Double getEndScore() {
		return endScore;
	}
	public void setEndScore(double endScore) {
		this.endScore = endScore;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
}
