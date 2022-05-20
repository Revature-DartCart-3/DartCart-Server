package com.revature.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.revature.models.ChatMessage;



@Controller
public class TechChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    @MessageMapping("/private-message")
    public ChatMessage recMessage(@Payload ChatMessage message){	
    	System.out.println(message.toString());
    	System.out.println(message.getRecipientId());
    	
        simpMessagingTemplate.convertAndSendToUser(message.getRecipientId(), "/private", message);
        return message;
    }
    
    //Handle initial message from client asking for help
    @MessageMapping("/help-request")
    public ChatMessage helpRequest(@Payload ChatMessage message){	
    	
        //simpMessagingTemplate.convertAndSendToUser(message.getRecipientId(), "/private", message);
    	System.out.println("Recieved request for help");
    	simpMessagingTemplate.convertAndSend("/chatroom/techies", message);
        
        return message;
    }
    
    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public ChatMessage receiveMessage(@Payload ChatMessage message){
        return message;
    }
    
    
    
	
}
