package com.dgit.domain;

import java.util.Date;

public class ProjectVO {
	private int pno;
	private int wno;
	private String title;
	private String explanation;
	private String visibility;
	private Date startDate;
	private Date endDate;
	private Date finishDate;
	private int status;
	private int authority;
	private boolean locker;
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getWno() {
		return wno;
	}
	public void setWno(int wno) {
		this.wno = wno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
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
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public boolean isLocker() {
		return locker;
	}
	public void setLocker(boolean locker) {
		this.locker = locker;
	}
	
	@Override
	public String toString() {
		return "ProjectVO [pno=" + pno + ", wno=" + wno + ", title=" + title + ", explanation=" + explanation
				+ ", visibility=" + visibility + ", startDate=" + startDate + ", endDate=" + endDate + ", finishDate="
				+ finishDate + ", status=" + status + ", authority=" + authority + ", locker=" + locker + "]";
	}
	
	
}
