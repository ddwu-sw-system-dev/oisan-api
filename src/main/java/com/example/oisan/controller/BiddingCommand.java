package com.example.oisan.controller;

public class BiddingCommand {
	private int price;
	private int auctionId;
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	
	@Override
	public String toString() {
		return "BiddingCommand [price=" + price + ", auctionId=" + auctionId + "]";
	}
	
}
