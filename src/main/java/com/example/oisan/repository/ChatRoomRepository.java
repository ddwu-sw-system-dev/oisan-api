package com.example.oisan.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oisan.domain.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
	
	ArrayList<ChatRoom> findChatRoomByCustomer1IdOrCustomer2Id(int customer1Id, int customer2Id);
	
	ChatRoom findChatRoomByCustomer1IdAndCustomer2Id (int customer1Id, int customer2Id);
	
	ChatRoom findChatRoomByChatRoomId (int chatRoomId);
}
