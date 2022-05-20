package com.revature.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.models.ChatMessage;
import com.revature.models.HelpSession;
import com.revature.models.SessionStatus;
import com.revature.services.HelpSessionService;



@Controller
public class TechChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    
    private HelpSessionService helpSessionService;
    
    
    //Handle sending message to a single user
    @MessageMapping("/private-message")
    public ChatMessage recMessage(@Payload ChatMessage message){	
    	System.out.println(message.toString());

    	//Send message to user based upon the recipientId within the message
        simpMessagingTemplate.convertAndSendToUser(Integer.toString(message.getRecipientId()), "/private", message);
        return message;
    }
    
    //Handle initial message from client asking for help
    @MessageMapping("/help-request")
    public ChatMessage helpRequest(@Payload ChatMessage message){	
    	
        //simpMessagingTemplate.convertAndSendToUser(message.getRecipientId(), "/private", message);
    	System.out.println("Recieved request for help");
    	System.out.println(message.getRecipientId());
    	
    	//create new session with the requesters user Id
    	helpSessionService.createSession(message.getSenderId());
    	
    	//send new request to listening techs
    	simpMessagingTemplate.convertAndSend("/chatroom/techies", message);
        
        return message;
    }
    
    
    //Get list of help Requests that have no assigned techs
    @RequestMapping("/help-request-list")
    public List<HelpSession> getHelpRequests() {
    	return (List<HelpSession>) helpSessionService.getAllBySessionStatus(SessionStatus.UNASSIGNED);
    }
	
}
