package com.dgit.domain;

public class JobAssignmentVO {
	private int pno;
	private int mno;
	private int taskno;
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getTaskno() {
		return taskno;
	}
	public void setTaskno(int taskno) {
		this.taskno = taskno;
	}
	@Override
	public String toString() {
		return "JobAssignmentVO [pno=" + pno + ", mno=" + mno + ", taskno=" + taskno + "]";
	}


}
