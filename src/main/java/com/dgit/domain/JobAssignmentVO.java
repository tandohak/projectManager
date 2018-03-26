package com.dgit.domain;

public class JobAssignmentVO {
	private int jno;
	private int taskno;
	private int mno;
	private int grade;

	public int getJno() {
		return jno;
	}

	public void setJno(int jno) {
		this.jno = jno;
	}

	public int getTaskno() {
		return taskno;
	}

	public void setTaskno(int taskno) {
		this.taskno = taskno;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "JobAssignmentVO [jno=" + jno + ", taskno=" + taskno + ", mno=" + mno + ", grade=" + grade + "]";
	}

}
