package com.dgit.domain;

public class MemAssignmentVO {
	private int pno;
	private int mno;
	private int grade;

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

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "MemAssignmentVO [pno=" + pno + ", mno=" + mno + ", grade=" + grade + "]";
	}

}
