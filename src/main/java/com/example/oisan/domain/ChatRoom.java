package com.example.oisan.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
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
	
	@OneToOne
	@JoinColumn(name="CUSTOMER1_Id", insertable=false, updatable=false)
	private Customer customer1;
	
	@OneToOne
	@JoinColumn(name="CUSTOMER2_ID", insertable=false, updatable=false)
	private Customer customer2;


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
	public Customer getCutomer1() {
		return customer1;
	}
	public void setCutomer1(Customer customer1) {
		this.customer1 = customer1;
	}
	public Customer getCutomer2() {
		return customer2;
	}
	public void setCutomer2(Customer customer2) {
		this.customer2 = customer2;
	}
}
