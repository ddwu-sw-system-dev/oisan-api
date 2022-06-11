package com.example.oisan.controller;

public class CustomerUpdateCommand {
	private int customerId;
	
	private String customerName;
	
	private String email;
	
	private String pw;
	
	private String confirmPw;
	
	private String address;
	
	private String phone;
	
	private String nickname;
	
	private int oiPay;
	
	//getter & setter
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
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
	public String getConfirmPw() {
		return confirmPw;
	}
	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setOiPay(int oiPay) {
		this.oiPay = oiPay;
	}
	public int getOiPay() {
		return this.oiPay;
	}
	
	// methods
	public boolean hasPw() {
		return pw != null && pw.trim().length() > 0;
	}
	
	public boolean isSamePwConfirmPw() {
		if (pw == null || confirmPw == null)
			return false;
		return pw.equals(confirmPw);
	}
	
	@Override
	public String toString() {
		return "Successfully updated!";
	}

}
