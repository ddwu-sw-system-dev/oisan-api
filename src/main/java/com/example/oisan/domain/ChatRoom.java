package com.example.oisan.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CHATROOM")
@SequenceGenerator(
        name = "CHAT_ROOM_SEQ_GENERATOR",
        sequenceName = "CHAT_ROOM_SEQ", // 시퀸스 명
        initialValue = 1, // 초기 값
        allocationSize = 1 // 미리 할당 받을 시퀸스 수
)
public class ChatRoom {
	
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CHAT_ROOM_SEQ_GENERATOR")
	private int chatRoomId;
	@Column(name="CUSTOMER1_ID")
	private int customer1Id;
	@Column(name="CUSTOMER2_ID")
	private int customer2Id;
	@Column
	private LocalDateTime updateAt;
	
	public ChatRoom() {	}
	
	public ChatRoom(int chatRoomId, int customer1Id, int customer2Id) {
		this.chatRoomId = chatRoomId;
		this.customer1Id = customer1Id;
		this.customer2Id = customer2Id;
	}
	
	public int getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(int chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	public int getCustomer1Id() {
		return customer1Id;
	}
	public void setCustomer1Id(int customer1Id) {
		this.customer1Id = customer1Id;
	}
	public int getCustomer2Id() {
		return customer2Id;
	}
	public void setCustomer2Id(int customer2Id) {
		this.customer2Id = customer2Id;
	}
	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}
}
