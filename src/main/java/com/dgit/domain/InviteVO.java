package com.dgit.domain;

import java.util.Date;

public class InviteVO {
	private int ino;
	private int wno;
	private Date inviteDate;
	private String inviter;
	private String invitee;
	
	public InviteVO() {}	
	
	public InviteVO(int ino, int wno, Date inviteDate, String inviter, String invitee) {
		super();
		this.ino = ino;
		this.wno = wno;
		this.inviteDate = inviteDate;
		this.inviter = inviter;
		this.invitee = invitee;
	}


	public int getIno() {
		return ino;
	}

	public void setIno(int ino) {
		this.ino = ino;
	}

	public int getWno() {
		return wno;
	}

	public void setWno(int wno) {
		this.wno = wno;
	}

	public Date getInviteDate() {
		return inviteDate;
	}

	public void setInviteDate(Date inviteDate) {
		this.inviteDate = inviteDate;
	}

	public String getInviter() {
		return inviter;
	}

	public void setInviter(String inviter) {
		this.inviter = inviter;
	}

	public String getInvitee() {
		return invitee;
	}

	public void setInvitee(String invitee) {
		this.invitee = invitee;
	}

	@Override
	public String toString() {
		return "Invite [ino=" + ino + ", wno=" + wno + ", inviteDate=" + inviteDate + ", inviter=" + inviter
				+ ", invitee=" + invitee + "]";
	}
}
