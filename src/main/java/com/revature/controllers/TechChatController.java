package com.revature.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.revature.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.revature.models.HelpSession;
import com.revature.services.HelpSessionService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TechChatController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private HelpSessionService helpSessionService;
	
	//Create automated messages object
	ChatMessage automatedMessage = new ChatMessage(0,"Automated System");
	
	
	// Handle sending message to a single user
	@MessageMapping("/private-message")
	public ChatMessage recMessage(@Payload ChatMessage message) {
		System.out.println(message.getRecipientId());
		System.out.println(message.getSenderId());
		// Send message to user based upon the recipientId within the message
		simpMessagingTemplate.convertAndSendToUser(Integer.toString(message.getRecipientId()), "/private", message);
		return message;
	}

	// Handle initial message from client asking for help
	@MessageMapping("/help-request")
	public ChatMessage helpRequest(@Payload ChatMessage message) {	
			// create new session with the requesters user Id, and store the id of the
			// created session
			System.out.println("Recieved request from user:" + message.getSenderId());
			HelpSession session = helpSessionService.createSession(message.getSenderId());

			// Send error message
			if (session == null) {
				automatedMessage.setType(MessageTypeEnum.Error);
				automatedMessage.setContent("Could Not Verify User");
				simpMessagingTemplate.convertAndSendToUser(Integer.toString(message.getSenderId()), "/private", automatedMessage);
				return message;
			}
			
			// Store the session id in the automatedMessage
			automatedMessage.setSessionId(session.getSessionId());
			
			// send a message back to the user containing the session Id
			automatedMessage.setType(MessageTypeEnum.Created);
			automatedMessage.setContent("Awaiting Tech Specialist...");
			simpMessagingTemplate.convertAndSendToUser(Integer.toString(message.getSenderId()), "/private", automatedMessage);
			


			// send new request to listening techs
			automatedMessage.setContent(session.toString());
			automatedMessage.setType(MessageTypeEnum.NewClient);
			simpMessagingTemplate.convertAndSend("/chatroom/techies", automatedMessage);


		
		return message;
	}

	// Handle either user sending a disconnect message
	@MessageMapping("/disconnect")
	public ChatMessage disconnectMessage(@Payload ChatMessage message) {
		System.out.println("user " + message.getSenderId() + " Disconnected");
		System.out.println("Completing session with id:" + message.getSessionId());
		
		//Create session response from session
		HelpSession session = helpSessionService.getSessionById(message.getSessionId());
		SessionResponse sessionResponse = new SessionResponse(session);
		
		//Tell techs in waiting to remove this session from their list if it in them. 
		//This helps to keep requests from persisting if a client disconnects before a tech is assigned to them
		automatedMessage.setContent(sessionResponse.toString());
		automatedMessage.setType(MessageTypeEnum.RemoveSession);
		simpMessagingTemplate.convertAndSend("/chatroom/techies", automatedMessage);
		
		// Set session as complete
		helpSessionService.setSessionComplete(message.getSessionId());
		
		//Create automatedMessage to let other user know that their counterpart left
		automatedMessage.setType(MessageTypeEnum.Leave);
		automatedMessage.setContent(message.getSenderName() + " Has Left");
		simpMessagingTemplate.convertAndSendToUser(Integer.toString(message.getRecipientId()), "/private",automatedMessage);
		
//		// send message to other techs letting them know session has ended.
//		automatedMessage.setContent(session.toString());
//		automatedMessage.setType(MessageTypeEnum.RemoveSession);
//		simpMessagingTemplate.convertAndSend("/chatroom/techies", automatedMessage);
		
		return message;
	}

	// Get list of help Requests that have no assigned techs
	@GetMapping("/help-request-list")
	public List<HelpSession> getHelpRequestsSessions() {
		System.out.println("Return List");
		List<HelpSession> sessions = (List<HelpSession>) helpSessionService.getAllBySessionStatus(SessionStatus.UNASSIGNED);
		System.out.println(sessions.toString());
		return sessions;
	}
	
	//Assign tech to client
	@PutMapping("/assign-tech")
	public SessionResponse connectTech(@RequestBody SessionResponse sessionResponse) {
	//public ChatMessage connectTech(@Payload ChatMessage message) {	//This is for setting it to listen on sockets rather then http Requests.
		HelpSession session = helpSessionService.setSessionAssigned(sessionResponse.getSessionId());
		if(session != null)  {
			
			//Create session data that will be sent to the client and tech This code is commented out as it is only used if this controller is listening on sockets rather then http requests
//			SessionResponse sessionResponse = new SessionResponse(session.getUser());
//			sessionResponse.setTechId(message.getSenderId());
//			sessionResponse.setTechName(message.getSenderName());
			
			//Send message to client letting them know what tech has been assigned to them
			automatedMessage.setContent(sessionResponse.toString());
			automatedMessage.setType(MessageTypeEnum.Join);
			simpMessagingTemplate.convertAndSendToUser(Integer.toString(sessionResponse.getClientId()), "/private", automatedMessage);
			
			//Send message to Tech letting them know they have successfully joined. This code is commented out as it is only used if this controller is listening on sockets rather then http requests
//			automatedMessage.setContent("Joined chat with " + session.getUser().getUsername());
//			simpMessagingTemplate.convertAndSendToUser(Integer.toString(message.getSenderId()), "/private", automatedMessage);
			
			//Send message to all listening techs letting them know that this client is no longer waiting
			automatedMessage.setType(MessageTypeEnum.RemoveSession);
			automatedMessage.setContent(sessionResponse.toString());
			simpMessagingTemplate.convertAndSend("/chatroom/techies", automatedMessage);
			System.out.println(session.toString());
			
			return sessionResponse;
		}
		//Send message to Tech that there was an error
		
//		automatedMessage.setType(MessageTypeEnum.Error);	This code is commented out as it is only used if this controller is listening on sockets rather then http requests
//		automatedMessage.setContent("Couldn't Join Session");
//		simpMessagingTemplate.convertAndSendToUser(Integer.toString(message.getRecipientId()), "/private", automatedMessage);
		
		return null;
	}
	
}

