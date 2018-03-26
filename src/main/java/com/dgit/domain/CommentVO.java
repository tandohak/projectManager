package com.dgit.domain;

public class CommentVO {
	private int cno;
	private int taskno;
	private int uno;
	private String writer;
	private String content;

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getTaskno() {
		return taskno;
	}

	public void setTaskno(int taskno) {
		this.taskno = taskno;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CommentVO [cno=" + cno + ", taskno=" + taskno + ", uno=" + uno + ", writer=" + writer + ", content="
				+ content + "]";
	}

}
