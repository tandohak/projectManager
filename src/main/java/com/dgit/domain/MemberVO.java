package com.dgit.domain;

public class MemberVO {
	private int mno;
	private int uno;
	private int wno;
	private int memGrade;

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public int getWno() {
		return wno;
	}

	public void setWno(int wno) {
		this.wno = wno;
	}

	public int getMemGrade() {
		return memGrade;
	}

	public void setMemGrade(int memGrade) {
		this.memGrade = memGrade;
	}

	@Override
	public String toString() {
		return "MemberVO [mno=" + mno + ", uno=" + uno + ", wno=" + wno + ", memGrade=" + memGrade + "]";
	}
}
