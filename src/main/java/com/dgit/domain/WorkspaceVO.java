package com.dgit.domain;

public class WorkspaceVO {
	private int wno;
	private String name;
	private String maker;
	private int uno;
	public int getWno() {
		return wno;
	}
	public void setWno(int wno) {
		this.wno = wno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	@Override
	public String toString() {
		return "WorkspaceVO [wno=" + wno + ", name=" + name + ", maker=" + maker + ", uno=" + uno + "]";
	}

}
