package com.example.oisan.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    @ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;
    private int categId;
    private Date createAt;
    private String title;
    @Column(name="p_desc")
    private String desc;
    private String imageUrl;
    @Column(name="f_width")
    private int width;
    @Column(name="f_height")
    private int height;
    @Column(name="f_depth")
    private int depth;
    private int status;

    public Post() {}

    public Post(int postId, Customer customer, int categId, Date createAt, String title, String desc, String imageUrl, int width, int height, int depth, int status) {
        this.postId = postId;
        this.customer = customer;
        this.categId = categId;
        this.createAt = createAt;
        this.title = title;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.status = status;
    }

    public Post(Customer customer, int categId, Date createAt, String title, String desc, String imageUrl, int width, int height, int depth, int status) {
        this.customer = customer;
        this.categId = categId;
        this.createAt = createAt;
        this.title = title;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.status = status;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getCategId() {
        return categId;
    }

    public void setCategId(int categId) {
        this.categId = categId;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
