package com.example.oisan.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MOODTAG")
@SequenceGenerator(
        name = "MOODTAG_GENERATOR",
        sequenceName = "MOODTAG_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
public class Moodtag {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MOODTAG_GENERATOR")
	private int moodtagId;
	
	private String name;
	
	public Moodtag() {}

	public Moodtag(int moodtagId, String name) {
		super();
		this.moodtagId = moodtagId;
		this.name = name;
	}

	public Moodtag(String name) {
		super();
		this.name = name;
	}

	public int getMoodtagId() {
		return moodtagId;
	}

	public void setMoodtagId(int moodtagId) {
		this.moodtagId = moodtagId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Moodtag [moodtagId=" + moodtagId + ", name=" + name + "]";
	}
	
}
