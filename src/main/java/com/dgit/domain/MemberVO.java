package com.dgit.domain;

public class MemberVO {
	private int mno;
	private int uno;
	private String wcode;
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
	public String getWcode() {
		return wcode;
	}
	public void setWcode(String wcode) {
		this.wcode = wcode;
	}
	public int getMemGrade() {
		return memGrade;
	}
	public void setMemGrade(int memGrade) {
		this.memGrade = memGrade;
	}
	@Override
	public String toString() {
		return "MemberVO [mno=" + mno + ", uno=" + uno + ", wcode=" + wcode + ", memGrade=" + memGrade + "]";
	}

}
