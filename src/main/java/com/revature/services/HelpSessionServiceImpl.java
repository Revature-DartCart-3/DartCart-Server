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

@Service
public class HelpSessionServiceImpl implements HelpSessionService {

	@Autowired
	HelpSessionRepo helpSessionRepo;
	
	@Autowired
	UserRepo userRepo;
	
	
	//Create new help session with the user, and set the status to UNASSIGNED. Returns the sessionId, and if no user is found, returns 0

	@Override
	public int createSession( int userId) {
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

	@Override
	public HelpSession setSessionAssigned(int sessionId) {
		Optional<HelpSession> sessionPotential = helpSessionRepo.findById(sessionId);
		
		if (sessionPotential.isPresent()) {
			HelpSession session = sessionPotential.get();
			session.setSessionStatus(SessionStatus.ASSIGNED);
			return session;
		}
		
		return null;
	}


//	@Override
//	public Optional<HelpSession> getSessionById(Integer id) {
//		return helpSessionRepo.findById(id);
//	}
//




//	@Override
//	public User getSessionByClient(AccountType accountType, int id) {
//		Optional<User> userOptional = userRepo.findById(id);
//		if(userOptional.isPresent()) {
//			if(userOptional.get().getAccountType() == AccountType.user) {
//				return userOptional.get();
//			} else {
//				System.out.println("USer Doesn't match required field");
//			}
//		} else {
//			System.out.println("User is not present");
//		}
//		return null;
//	}

//	@Override
//	public User getSessionByAdmin(AccountType accountType, int id){
//		Optional<User> adminOptional = userRepo.findById(id);
//		if(adminOptional.isPresent()) {
//			if(adminOptional.get().getAccountType() == accountType.admin) {
//				return adminOptional.get();
//			} else {
//				System.out.println("USer Doesn't match required field");
//			}
//		}else {
//			System.out.println("User wasn't found");
//		}
//		return null;
//	}


}
