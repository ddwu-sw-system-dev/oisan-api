package com.example.oisan.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.oisan.domain.Chat;
import com.example.oisan.domain.ChatRoom;
import com.example.oisan.repository.ChatRepository;
import com.example.oisan.repository.ChatRoomRepository;

@Service
public class ChatRoomService {
//	private Map<Integer, ChatRoom> chatRoomMap = new HashMap<Integer, ChatRoom>();
	
	@Autowired
	private ChatRoomRepository chatRoomRepository;
	public void setChatRoomRepository(ChatRoomRepository chatRoomRepository) {
		this.chatRoomRepository = chatRoomRepository;
	}
	
	@Autowired
	private ChatRepository chatRepository;
	public void setChatRepository(ChatRepository chatRoomRepository) {
		this.chatRepository = chatRepository;
	}
	
	public ChatRoomService() {
//		ArrayList<Chat> clist1 = new ArrayList<Chat>();
//		clist1.add(new Chat(1, 0, "hi there, i am test2"));
//		clist1.add(new Chat(0, 0, "hi i am test1"));
//		clist1.add(new Chat(1, 0, "nice to meet you"));
//		clist1.add(new Chat(0, 0, "have a nice day"));
//		clist1.add(new Chat(1, 0, "bye"));
//		clist1.add(new Chat(0, 0, "bye!"));
//		chatRoomMap.put(0, new ChatRoom(0, 0, 1, clist1));
	}

	public ChatRoom createChatRoomByCustomerId(int customer1Id, int customer2Id) {
		if (customer1Id > customer2Id) {
			int tempCustomerId = customer1Id;
			customer1Id = customer2Id;
			customer2Id = tempCustomerId;
		}
		ChatRoom newChatRoom = new ChatRoom();
		newChatRoom.setCustomer1Id(customer1Id);
		newChatRoom.setCustomer2Id(customer2Id);
		newChatRoom.setUpdateAt(LocalDateTime.now());
		return chatRoomRepository.save(newChatRoom);
	}
	
	public void deleteChatRoom(int chatRoomId) {
		chatRoomRepository.deleteById(chatRoomId);
	}
	
	public ArrayList<ChatRoom> getCustomerChatRoomList(int loginCustomerId){
//		ArrayList<ChatRoom> customerChatRoomList = new ArrayList<ChatRoom>();
//		for (ChatRoom cr : chatRoomMap.values()) {
//			if (cr.getCustomer1Id() == loginCustomerId | cr.getCustomer2Id() == loginCustomerId) {
//				customerChatRoomList.add(cr);
//			}
//		}
//		return customerChatRoomList;
		return chatRoomRepository.findChatRoomByCustomer1IdOrCustomer2Id(loginCustomerId, loginCustomerId);
	}
	
//	public ArrayList<Chat> getChatListsByChatRoomId(int id){
//		ChatRoom chatRoom = chatRoomMap.get(id);
//		return chatRoom.getChatList();
//	}
	
	public ChatRoom findChatRoomByCustomerId(int customer1Id, int customer2Id) {
		if (customer1Id > customer2Id) {
			int tempCustomerId = customer1Id;
			customer1Id = customer2Id;
			customer2Id = tempCustomerId;
		}
		return chatRoomRepository.findChatRoomByCustomer1IdAndCustomer2Id(customer1Id, customer2Id);
	}
	
	public ChatRoom findChatRoomByChatRoomId(int chatRoomId) {
		return chatRoomRepository.findChatRoomByChatRoomId(chatRoomId);
	}
	
	public ArrayList<Chat> sendChat(Chat newChat, int chatRoomId) {
		chatRepository.save(newChat);
		return chatRepository.findChatByChatRoomId(chatRoomId);
	}
	
	public ArrayList<Chat> findChatList(int chatRoomId) {
		return chatRepository.findChatByChatRoomId(chatRoomId);
	}
	
	public String toString() {
		return "----------------chatroomservice tostring";
	}
	
}
