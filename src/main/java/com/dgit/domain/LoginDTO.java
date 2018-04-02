package com.dgit.domain;

public class LoginDTO {
	private int uno;
	private String email;
	private String password;
	private String username;

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "LoginDTO [email=" + email + ", password=" + password + ", username=" + username + "]";
	}

}
