package com.example.oisan.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CHAT")
@SequenceGenerator(
        name = "CHAT_SEQ_GENERATOR",
        sequenceName = "CHAT_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
public class Chat {
	@Id
	@Column(name="CHAT_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CHAT_SEQ_GENERATOR")
	private int chatId;
	
	@Column(name="CUSTOMER_ID")
	private int senderId;
	
	@Column(name="CHAT_ROOM_ID")
	private int chatRoomId;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="CREATE_AT")
	private LocalDateTime createAt;
	
	public Chat() {}
	
	public Chat(int senderId, int chatRoomId, String content) {
		this.senderId = senderId;
		this.chatRoomId = chatRoomId;
		this.content = content;
		this.createAt = LocalDateTime.now();
	}	
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(int chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
		
}
