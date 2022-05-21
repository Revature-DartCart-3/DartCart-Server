package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "help_sessions")
public class HelpSession {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sessionId;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
  
	@Column(name = "session_status")
	private SessionStatus sessionStatus;
}



