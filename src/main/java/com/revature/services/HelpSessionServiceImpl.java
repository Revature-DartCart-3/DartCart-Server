package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.HelpSession;
import com.revature.models.SessionStatus;
import com.revature.models.User;
import com.revature.repositories.HelpSessionRepo;
import com.revature.repositories.UserRepo;

import antlr.TokenWithIndex;

@Service
public class HelpSessionServiceImpl implements HelpSessionService {

	@Autowired
	HelpSessionRepo helpSessionRepo;
	
	@Autowired
	UserRepo userRepo;
	
	
	//Create new help session with the user, and set the status to UNASSIGNED. Returns the sessionId, and if no user is found, returns 0
	@Override
	public int createSession(int userId) {
		Optional user = userRepo.findById(userId);
		if (user.isPresent()) {

			//Get the user that is requesting help
			User client = (User) user.get();
						
			//Create a session with the user
			HelpSession newHelpSession = new HelpSession();
			newHelpSession.setUser(client);
			newHelpSession.setSessionStatus(SessionStatus.UNASSIGNED);
			newHelpSession = helpSessionRepo.save(newHelpSession);
			
			//Return the id of the newly created session
			return newHelpSession.getSessionId();
		}
		System.out.println("Couldn't Find User");
		return 0;
		
	}
	
	@Override
	public Optional<HelpSession> getSessionById(int id) {
		return helpSessionRepo.findById(id);
	}

	@Override
	public Optional<HelpSession> getSessionByUserId(int id) {
		return helpSessionRepo.findByUser(id);
	}

//	@Override
//	public Optional<HelpSession> getSessionByTechId(int id) {
//		return helpSessionRepo.findByTech(id);
//	}

	@Override
	public List<HelpSession> getAllBySessionStatus(SessionStatus sessionStatus) {
		return helpSessionRepo.findAllBySessionStatus(sessionStatus);
	}

	@Override
	public void setSessionComplete(int id) {
		helpSessionRepo.deleteById(id);
		
		
	}

}
