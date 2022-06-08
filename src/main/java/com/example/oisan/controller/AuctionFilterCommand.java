package com.example.oisan.controller;

public class AuctionFilterCommand {
	private int minWidth;
	private int minDepth;
	private int minHeight;
	private int maxWidth;
	private int maxDepth;
	private int maxHeight;
	
	public AuctionFilterCommand(int minWidth, int minDepth, int minHeight, int maxWidth,
			int maxDepth, int maxHeight) {
		super();
		this.minWidth = minWidth;
		this.minDepth = minDepth;
		this.minHeight = minHeight;
		this.maxWidth = maxWidth;
		this.maxDepth = maxDepth;
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
	
}
