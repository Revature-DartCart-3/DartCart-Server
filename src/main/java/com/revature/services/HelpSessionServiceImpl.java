package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.models.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.HelpSession;
import com.revature.models.SessionStatus;
import com.revature.models.User;
import com.revature.repositories.HelpSessionRepo;
import com.revature.repositories.UserRepo;
import org.springframework.util.RouteMatcher;

@Service
public class HelpSessionServiceImpl implements HelpSessionService {

	private final UserRepo userRepo;
	private final HelpSessionRepo helpSessionRepo;

	@Autowired
	public HelpSessionServiceImpl(UserRepo userRepo, HelpSessionRepo helpSessionRepo) {
		this.userRepo = userRepo;
		this.helpSessionRepo = helpSessionRepo;
	}


	//Create new help session with the user, and set the status to UNASSIGNED
	@Override
	public void createSession(int userId) {
		Optional user = userRepo.findById(userId);
		if (user.isPresent()) {
			User client = (User) user.get();

			System.out.println(client);

			HelpSession newHelpSession = null;
			newHelpSession.setUser(client);
			newHelpSession.setSessionStatus(SessionStatus.UNASSIGNED);

			helpSessionRepo.save(newHelpSession);
		}

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

	@Override
	public List<HelpSession> getAllBySessionStatus(SessionStatus sessionStatus) {
		return helpSessionRepo.findAllBySessionStatus(sessionStatus);
	}

	@Override
	public User getSessionByClient(User user, AccountType accountType, int id) {
		Optional<User> userOptional = userRepo.findById(id);
		if(userOptional.isPresent()) {
			if(userOptional.get().getAccountType() == AccountType.user) {
				return userOptional.get();
			} else {
				System.out.println("USer Doesn't match required field");
			}
		} else {
			System.out.println("User is not present");
		}
		return null;
	}



}
