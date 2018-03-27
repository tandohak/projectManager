package com.dgit.domain;

import java.util.Date;

public class WorkspaceVO {
	private int wno;
	private String name;
	private String maker;
	private int uno;
	private Date regDate;

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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "WorkspaceVO [wno=" + wno + ", name=" + name + ", maker=" + maker + ", uno=" + uno + ", regDate="
				+ regDate + "]";
	}
	
}
