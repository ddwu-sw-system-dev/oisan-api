package com.example.oisan.controller;

import java.util.Date;

public class OiPayUsageRechargeCommand {

	private int userId;
	private int oiPayId;

	private int type;
	
	private int amount;
	
	private int remain;
	private int auctionId;
	private Date createAt;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getOiPayId() {
		return oiPayId;
	}
	public void setOiPayId(int oiPayId) {
		this.oiPayId = oiPayId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	@Override
	public String toString() {
		return "OiPayUsageChargeRequest [userId=" + userId + ", oiPayId=" + oiPayId + ", type=" + type + ", amount="
				+ amount + ", remain=" + remain + ", auctionId=" + auctionId + ", createAt=" + createAt + "]";
	}
	
	
}