package com.example.oisan.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="LIKE_POST")
@SequenceGenerator(
        name = "LIKE_POST_SEQ_GENERATOR",
        sequenceName = "LIKE_POST_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
public class PostLike {

	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LIKE_POST_SEQ_GENERATOR")
	private int likeId;
	@Column(name="POST_ID")
	private int postId;
	@Column(name="CUSTOMER_ID")
	private int customerId;
	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
}
