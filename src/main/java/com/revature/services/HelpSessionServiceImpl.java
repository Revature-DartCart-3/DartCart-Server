package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.models.AccountType;
import com.sun.corba.se.impl.ior.OldPOAObjectKeyTemplate;
import com.sun.org.apache.bcel.internal.generic.ATHROW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.revature.models.HelpSession;
import com.revature.models.SessionStatus;
import com.revature.models.User;
import com.revature.repositories.HelpSessionRepo;
import com.revature.repositories.UserRepo;
import org.springframework.util.RouteMatcher;

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
			User client = (User) user.get();

			System.out.println(client);

			HelpSession newHelpSession = new HelpSession();

//			HelpSession newHelpSession = null;
			newHelpSession.setUser(client);
			newHelpSession.setSessionStatus(SessionStatus.UNASSIGNED);

			helpSessionRepo.save(newHelpSession);
			return (helpSessionRepo.save(newHelpSession)).getSessionId();

		} else {
			return 0;

		}

	}


	@Override
	public Optional<HelpSession> getSessionById(int id) {
		return helpSessionRepo.findById(id);
	}

	@Override
	public List<HelpSession> getAllBySessionStatus(SessionStatus sessionStatus) {
		return helpSessionRepo.findAllBySessionStatus(sessionStatus);
	}

	@Override
	public void setSessionComplete(int id) {
		helpSessionRepo.deleteById(id);
	}


//	@Override
//	public Optional<HelpSession> getSessionById(Integer id) {
//		return helpSessionRepo.findById(id);
//	}
//
//	@Override
//	public Optional<HelpSession> getSessionByUserId(Integer id) {
//		return helpSessionRepo.findByUser(id);
//	}
//
//	@Override
//	public Optional<HelpSession> getSessionByTechId(Integer id) {
//		return helpSessionRepo.findByTech(id);
//	}



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
