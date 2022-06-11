package com.example.oisan.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oisan.domain.Chat;
import com.example.oisan.domain.ChatRoom;
import com.example.oisan.service.ChatRoomService;

@RestController
@RequestMapping("/chatRooms")
public class ChatRoomController {
	
	@Autowired
	private ChatRoomService chatRoomService;
	public void setchatRoomService(ChatRoomService chatRoomService) {
		this.chatRoomService = chatRoomService;
	}
	
	@PostMapping("/create/{customer1}/{customer2}")
	public ChatRoom create(@PathVariable String customer1, @PathVariable String customer2) {
		int customer1Id = Integer.parseInt(customer1);
		int customer2Id = Integer.parseInt(customer2);
		return chatRoomService.createChatRoomByCustomerId(customer1Id, customer2Id);
	}
	
	@PostMapping("/sendChat/{chatRoomId}")
	public ArrayList<Chat> sendChat(@PathVariable int chatRoomId, Chat newChat) {
		return chatRoomService.sendChat(newChat, chatRoomId);
	}
	
	@GetMapping("/getChatList/{chatRoomId}")
	public ArrayList<Chat> getChatList(@PathVariable int chatRoomId) {
		return chatRoomService.findChatList(chatRoomId);
	}
	
	@DeleteMapping
	public void delete(@PathVariable String chatRoomId) {
		int id = Integer.parseInt(chatRoomId);
		chatRoomService.deleteChatRoom(id);
	}
	
	@GetMapping("/list/{customerId}")
	public ArrayList<ChatRoom> getCustomerChatRoomListByUsesrId(@PathVariable String customerId) {
		int id = Integer.parseInt(customerId);
		return chatRoomService.getCustomerChatRoomList(id);
	}
	
	@GetMapping("/{chatRoomId}")
	public ChatRoom getChatRoom(@PathVariable String chatRoomId){
		int id = Integer.parseInt(chatRoomId);
		return chatRoomService.findChatRoomByChatRoomId(id);
	}

}