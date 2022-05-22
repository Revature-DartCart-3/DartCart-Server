package com.revature.models;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SessionResponse {
	private int sessionId;
	private int techId;
	private String techName;
	
	private int clientId;
	private String clientName;
	
	//Allow passing of user to set the client data
	public SessionResponse (User client) {
		this.clientId = client.getId();
		this.clientName = client.getUsername();
	}
	
	
	
	public int getTechId() {
		return techId;
	}
	public void setTechId(int techId) {
		this.techId = techId;
	}
	public String getTechName() {
		return techName;
	}
	public void setTechName(String techName) {
		this.techName = techName;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public int getSessionId() {
		return sessionId;
	}



	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}



	@Override
	public String toString() {
		return "SessionResponse [sessionId=" + sessionId + ", techId=" + techId + ", techName=" + techName
				+ ", clientId=" + clientId + ", clientName=" + clientName + "]";
	}
	
	
}
