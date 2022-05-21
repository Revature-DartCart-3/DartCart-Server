package com.revature.models;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage implements Cloneable{

	
    @Id
    private int id;
    private int sessionId;
    private int senderId;
    private int recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    
    
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	@Override
	public String toString() {
		return "ChatMessage [id=" + id + ", sessionId=" + sessionId + ", senderId=" + senderId + ", recipientId="
				+ recipientId + ", senderName=" + senderName + ", recipientName=" + recipientName + ", content="
				+ content + "]";
	}
    

	public Object clone() throws CloneNotSupportedException{  
		return super.clone();  
	}
	public ChatMessage(int senderId, String senderName) {
		super();
		this.senderId = senderId;
		this.senderName = senderName;
	}
	
	
	
	
    
}
