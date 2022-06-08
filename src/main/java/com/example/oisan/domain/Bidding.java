package com.example.oisan.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Bidding")
@SequenceGenerator(
        name = "BIDDING_GENERATOR",
        sequenceName = "BIDDING_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
public class Bidding {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BIDDING_SEQ_GENERATOR")
	private int biddingId;

	private int customerId;
	private int auctionId;
	private int price;
	private Date createAt;
	
	public Bidding() {}
	
	public Bidding(int biddingId, int customerId, int auctionId, int price, Date createAt) {
		super();
		this.biddingId = biddingId;
		this.customerId = customerId;
		this.auctionId = auctionId;
		this.price = price;
		this.createAt = createAt;
	}
	
	public Bidding(int customerId, int auctionId, int price, Date createAt) {
		super();
		this.customerId = customerId;
		this.auctionId = auctionId;
		this.price = price;
		this.createAt = createAt;
	}

	public int getBiddingId() {
		return biddingId;
	}
	public void setBiddingId(int biddingId) {
		this.biddingId = biddingId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
}
