package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.models.HelpSession;
import com.revature.models.SessionStatus;

public interface HelpSessionService {
	
	
	public void createSession(int userId);
	public Optional<HelpSession> getSessionById(int id);
	public Optional<HelpSession> getSessionByUserId(int id);
	public Optional<HelpSession> getSessionByTechId(int id);
	public List<HelpSession> getAllBySessionStatus(SessionStatus sessionStatus);
}
