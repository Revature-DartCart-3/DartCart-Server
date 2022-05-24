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
	public HelpSession createSession( int userId) {
		Optional user = userRepo.findById(userId);
		if (user.isPresent()) {

			//Get the user that is requesting help
			User client = (User) user.get();
			
			//Create a session with the user
			HelpSession newHelpSession = new HelpSession();
			newHelpSession.setClient(client);
			newHelpSession.setSessionStatus(SessionStatus.UNASSIGNED);
			newHelpSession = helpSessionRepo.save(newHelpSession);
			
			//Return the newly created session
			return newHelpSession;
		}
		System.out.println("Couldn't Find User");
		return null;
		
	}
	
	@Override
	public HelpSession getSessionById(int id) {
		Optional<HelpSession> sessionPotential = helpSessionRepo.findById(id);
		if (sessionPotential.isPresent()) {
			HelpSession session = sessionPotential.get();
			return session;
		}
		return null;
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
		System.out.println("************************************************");
		if (sessionPotential.isPresent()) {
			HelpSession session = sessionPotential.get();
			System.out.println(session.toString());
			session.setSessionStatus(SessionStatus.ASSIGNED);
			helpSessionRepo.save(session);
			System.out.println(session.toString());
			System.out.println("************************************************");
			return session;
		}
		System.out.println("Session Not Found");
		System.out.println("************************************************");
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
