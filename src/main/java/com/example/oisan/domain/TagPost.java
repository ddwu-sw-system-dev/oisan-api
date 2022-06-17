package com.example.oisan.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TAG_POST")
@SequenceGenerator(
        name = "TAG_POST_GENERATOR",
        sequenceName = "TAG_POST_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
public class TagPost {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAG_POST_GENERATOR")
	private int tagPostId;
	
	@ManyToOne
	@JoinColumn(name="MOODTAG_ID")
	private Moodtag moodtag;

	@ManyToOne
	@JoinColumn(name="POST_ID")
	private Post post;
	
	public TagPost() {}

	public TagPost(int tagPostId, Moodtag moodtag, Post post) {
		super();
		this.tagPostId = tagPostId;
		this.moodtag = moodtag;
		this.post = post;
	}

	public TagPost(Moodtag moodtag, Post post) {
		super();
		this.moodtag = moodtag;
		this.post = post;
	}

	public int getTagPostId() {
		return tagPostId;
	}

	public void setTagPostId(int tagPostId) {
		this.tagPostId = tagPostId;
	}

	public Moodtag getMoodtag() {
		return moodtag;
	}

	public void setMoodtag(Moodtag moodtag) {
		this.moodtag = moodtag;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "TagPost [tagPostId=" + tagPostId + ", moodtag=" + moodtag + ", post=" + post + "]";
	}
	
}
