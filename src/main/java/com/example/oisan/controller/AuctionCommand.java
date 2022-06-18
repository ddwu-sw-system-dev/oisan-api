package com.example.oisan.controller;

import org.springframework.web.multipart.MultipartFile;

public class AuctionCommand {
	private int auctionId;
	private String title;
	private String desc;
	private MultipartFile image;
	private int categId;
	private int width;
	private int depth;
	private int height;
	private int price;
	
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
	
	public MultipartFile getImage() {
		return image;
	}
	
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	public int getCategId() {
		return categId;
	}
	
	public void setCategId(int categId) {
		this.categId = categId;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
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
		return "AuctionCommand [auctionId= " + auctionId + ", title=" + title + ", desc=" + desc + ", imageUrl=" + image + ", categoryId="
				+ categId + ", width=" + width + ", depth=" + depth + ", height=" + height + ", price=" + price
				+ "]";
	}
	
}
