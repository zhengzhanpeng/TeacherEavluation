package com.hdxy.pojo;

public class EndScoreInput {
	private String jobNumber;
	private String name;
	private Integer semester;
	private Double endScore;
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
	public Integer getSemester() {
		return semester;
	}
	public void setSemester(Integer semester) {
		this.semester = semester;
	}
	public Double getEndScore() {
		return endScore;
	}
	public void setEndScore(Double endScore) {
		this.endScore = endScore;
	}
}
