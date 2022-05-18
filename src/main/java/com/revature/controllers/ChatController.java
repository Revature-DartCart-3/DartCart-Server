package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.revature.models.ChatMessage;

import lombok.var;

public class ChatController {

	
	@Autowired private SimpMessagingTemplate messagingTemplate;
    @Autowired private ChatMessageService chatMessageService;
    @Autowired private ChatRoomService chatRoomService;
    
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
    var chatId = chatRoomService       
    .getChatId(chatMessage.getSenderId(), 
    chatMessage.getRecipientId(), true);  
          
    chatMessage.setChatId(chatId.get());
    
    ChatMessage saved = chatMessageService.save(chatMessage);     
    
    messagingTemplate.convertAndSendToUser(                
          chatMessage.getRecipientId(),"/queue/messages",
          new ChatNotification(                        
                saved.getId(),                        
                saved.getSenderId(),                        
                saved.getSenderName())
    };
}
