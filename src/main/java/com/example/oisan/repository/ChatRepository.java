package com.example.oisan.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oisan.domain.Chat;
import com.example.oisan.domain.ChatRoom;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
	ArrayList<Chat> findChatListByChatRoomId(int chatRoomId);
}
