package com.example.oisan.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
@SequenceGenerator(
        name = "CUSTOMER_SEQ_GENERATOR",
        sequenceName = "CUSTOMER_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
public class Customer {
	@Column(name="customer_id")
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CUSTOMER_SEQ_GENERATOR")
	private int customerId;
	@Column(name="customer_name")
	private String customerName;
	@Column
	private String email;
	@Column
	private String pw;
	@Column
	private String address;
	@Column
	private String phone;
	@Column
	private String nickname;
	@Column(name="oi_pay")
	private int oiPay;
	
	public Customer() {}
	
	public Customer(int customerId, String customerName, String email, String pw, String address, String phone, String nickname) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.pw = pw;
		this.address = address;
		this.phone = phone;
		this.nickname = nickname;
	}
	
	public Customer(String customerName, String email, String pw, String address, String phone, String nickname) {
		this.customerName = customerName;
		this.email = email;
		this.pw = pw;
		this.address = address;
		this.phone = phone;
		this.nickname = nickname;
	}
	
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
	public int getOiPay() {
		return oiPay;
	}
	public void setOiPay(int oiPay) {
		this.oiPay = oiPay;
	}
	public String toString() {
		return this.customerName;
	}
	
}