package com.dgit.domain;

public class MemberVO {
	private int mno;
	private int uno;
	private int wno;
	private Boolean permission;
	
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
	public Boolean getPermission() {
		return permission;
	}
	public void setPermission(Boolean permission) {
		this.permission = permission;
	}
	
	@Override
	public String toString() {
		return "MemberVO [mno=" + mno + ", uno=" + uno + ", wno=" + wno + ", permission=" + permission + "]";
	}
}
