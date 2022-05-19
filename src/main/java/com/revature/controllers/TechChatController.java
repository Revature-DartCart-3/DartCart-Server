package com.revature.controllers;

<<<<<<< Updated upstream
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

=======
import com.revature.models.ChatMessage;




import com.revature.models.OutputMessage;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.revature.models.ChatMessage;
import com.revature.models.OutputMessage;

@Controller
public class TechChatController {
<<<<<<< Updated upstream

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
=======
	
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

//    @MessageMapping("/secured/room")
//    public void sendSpecific(
//            @Payload ChatMessage msg,
//            Principal user,
//            @Header("simpSessionId") String sessionId) throws Exception {
//        OutputMessage out = new OutputMessage(
//                msg.getSenderId(),
//                msg.getContent(),
//                new SimpleDateFormat("HH:mm").format(new Date()));
//        simpMessagingTemplate.convertAndSendToUser(
//                msg.getRecipientId(), "/queue/specific-user", out);
//    }
    
    @MessageMapping("/private-message")
    public ChatMessage recMessage(@Payload ChatMessage message){
        simpMessagingTemplate.convertAndSendToUser(message.getRecipientId(),"/private",message);
        System.out.println(message.toString());
        return message;
    }
    
    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public ChatMessage receiveMessage(@Payload ChatMessage message){
        return message;
    }
    
    
>>>>>>> Stashed changes

	@MessageMapping("/secured/room") 
	public void sendSpecific(
	  @Payload ChatMessage msg, 
	  Principal user, 
	  @Header("simpSessionId") String sessionId) throws Exception { 
	    OutputMessage out = new OutputMessage(
	    msg.getSenderId(), 
	    msg.getContent(),
	    new SimpleDateFormat("HH:mm").format(new Date())); 
	    simpMessagingTemplate.convertAndSendToUser(
	    msg.getRecipientId(), "/queue/specific-user", out);
	}
	
}
