package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.models.AccountType;
import com.revature.models.HelpSession;
import com.revature.models.SessionStatus;
import com.revature.models.User;

public interface HelpSessionService {
	
	public HelpSession createSession(int userId);
	public HelpSession getSessionById(int id);
//	public Optional<HelpSession> getSessionByUserId(int id);
	//public Optional<HelpSession> getSessionByTechId(int id);
	public List<HelpSession> getAllBySessionStatus(SessionStatus sessionStatus);
	public void setSessionComplete(int sessionId);
	public HelpSession setSessionAssigned(int sessionId);
	public void deleteSession(int sessionId);

	//	@Override
	//	public Optional<HelpSession> getSessionById(Integer id) {
	//		return helpSessionRepo.findById(id);
	//	}
	//
}
