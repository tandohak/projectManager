package com.dgit.domain;

import java.util.Date;

public class TaskVO {
	private int taskno;
	private int tlno;
	private String explanation;
	private String taskname;
	private Date startDate;
	private Date endDate;
	private Date workingtime; 
	private int cycle;
	public int getTaskno() {
		return taskno;
	}
	public void setTaskno(int taskno) {
		this.taskno = taskno;
	}
	public int getTlno() {
		return tlno;
	}
	public void setTlno(int tlno) {
		this.tlno = tlno;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getWorkingtime() {
		return workingtime;
	}
	public void setWorkingtime(Date workingtime) {
		this.workingtime = workingtime;
	}
	public int getCycle() {
		return cycle;
	}
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	@Override
	public String toString() {
		return "TaskVO [taskno=" + taskno + ", tlno=" + tlno + ", explanation=" + explanation + ", taskname=" + taskname
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", workingtime=" + workingtime + ", cycle="
				+ cycle + "]";
	}
}
