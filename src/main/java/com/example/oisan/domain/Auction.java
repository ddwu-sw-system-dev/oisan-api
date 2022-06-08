package com.example.oisan.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AUCTION")
@SequenceGenerator(
        name = "AUCTION_GENERATOR",
        sequenceName = "AUCTION_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
public class Auction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUCTION_SEQ_GENERATOR")
	private int auctionId;
	
	private int customerId;
	private int startBid; // 시작가
	private int winningBid; // 최종가/현재가
	private Date createAt;
	private Date closingTime;
	private int status;
	private String title;
	private String imageUrl;
	
	@Column(name="categ_id")
	private int categoryId;
	
	@Column(name="a_desc")
	private String desc;
	
	@Embedded
	private Furniture furniture;
	
	public Auction() {}

	public Auction(int customerId, int startBid, int winningBid, Date createAt, Date closingTime, int status,
			String title, String imageUrl, int categoryId, String desc, Furniture furniture) {
		super();
		this.customerId = customerId;
		this.startBid = startBid;
		this.winningBid = winningBid;
		this.createAt = createAt;
		this.closingTime = closingTime;
		this.status = status;
		this.title = title;
		this.imageUrl = imageUrl;
		this.categoryId = categoryId;
		this.desc = desc;
		this.furniture = furniture;
	}
	public Auction(int auctionId, int customerId, int startBid, int winningBid, Date createAt, Date closingTime,
			int status, String title, String imageUrl, int categoryId, String desc, Furniture furniture) {
		super();
		this.auctionId = auctionId;
		this.customerId = customerId;
		this.startBid = startBid;
		this.winningBid = winningBid;
		this.createAt = createAt;
		this.closingTime = closingTime;
		this.status = status;
		this.title = title;
		this.imageUrl = imageUrl;
		this.categoryId = categoryId;
		this.desc = desc;
		this.furniture = furniture;
	}
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getStartBid() {
		return startBid;
	}
	public void setStartBid(int startBid) {
		this.startBid = startBid;
	}
	public int getWinningBid() {
		return winningBid;
	}
	public void setWinningBid(int winningBid) {
		this.winningBid = winningBid;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public Furniture getFurniture() {
		return furniture;
	}
	public void setFurniture(Furniture furniture) {
		this.furniture = furniture;
	}
	public Date getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Auction [auctionId=" + auctionId + ", customerId=" + customerId + ", startBid=" + startBid
				+ ", winningBid=" + winningBid + ", createAt=" + createAt + ", closingTime=" + closingTime + ", status="
				+ status + ", title=" + title + ", imageUrl=" + imageUrl + ", categoryId=" + categoryId + ", desc="
				+ desc + ", furniture=" + furniture + "]";
	}
	
}
