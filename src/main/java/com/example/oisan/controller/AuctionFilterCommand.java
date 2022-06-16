package com.example.oisan.controller;

public class AuctionFilterCommand {
	private int minWidth;
	private int maxWidth;
	private int minDepth;
	private int maxDepth;
	private int minHeight;
	private int maxHeight;
		
	public AuctionFilterCommand(int minWidth, int maxWidth, int minDepth, int maxDepth, int minHeight, int maxHeight) {
		super();
		this.minWidth = minWidth;
		this.maxWidth = maxWidth;
		this.minDepth = minDepth;
		this.maxDepth = maxDepth;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
	}
	public int getMinWidth() {
		return minWidth;
	}
	public void setMinWidth(int minWidth) {
		this.minWidth = minWidth;
	}
	public int getMinDepth() {
		return minDepth;
	}
	public void setMinDepth(int minDepth) {
		this.minDepth = minDepth;
	}
	public int getMinHeight() {
		return minHeight;
	}
	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}
	public int getMaxWidth() {
		return maxWidth;
	}
	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}
	public int getMaxDepth() {
		return maxDepth;
	}
	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}
	public int getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	@Override
	public String toString() {
		return "AuctionFilterCommand [minWidth=" + minWidth + ", minDepth=" + minDepth + ", minHeight=" + minHeight
				+ ", maxWidth=" + maxWidth + ", maxDepth=" + maxDepth + ", maxHeight=" + maxHeight + "]";
	}
	
}
