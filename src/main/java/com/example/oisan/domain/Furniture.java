package com.example.oisan.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Furniture {

	@Column(name="f_width")
	private int width;

	@Column(name="f_depth")
	private int depth;

	@Column(name="f_height")
	private int height;
	
	public Furniture() {}
	
	public Furniture(int width, int depth, int height) {
		super();
		this.width = width;
		this.depth = depth;
		this.height = height;
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

	
}
