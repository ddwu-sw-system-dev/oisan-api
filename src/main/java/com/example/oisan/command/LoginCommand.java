package com.example.oisan.command;


public class LoginCommand {

	private String email;
	private String pw;
	private int customerCustomerId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getCustomerId() {
		return customerCustomerId;
	}

	public void setCustomerId(int customerCustomerId) {
		this.customerCustomerId = customerCustomerId;
	}
	

}
