package com.example.oisan.controller;

public class CustomerCommand {

    private int customerId;

    private String customerName;

    private String email;

    private String pw;

    private String confirmPw;

    private String address;

    private String phone;

    private String nickname;

    public CustomerCommand() {}

    public CustomerCommand(int customerId, String customerName, String email, String pw, String confirmPw, String address, String phone, String nickname) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.pw = pw;
        this.confirmPw = confirmPw;
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
}
