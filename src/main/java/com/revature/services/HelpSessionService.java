package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.models.AccountType;
import com.revature.models.HelpSession;
import com.revature.models.SessionStatus;
import com.revature.models.User;

public interface HelpSessionService {
	

	public void createSession(int userId);
//	public Optional<HelpSession> getSessionById(Integer id);
//	public Optional<HelpSession> getSessionByUserId(Integer id);
//	public Optional<HelpSession> getSessionByTechId(Integer id);
	public List<HelpSession> getAllBySessionStatus(SessionStatus sessionStatus);

	User getSessionByClient(AccountType accountType, int id);

	User getSessionByAdmin(AccountType accountType, int id);
}
