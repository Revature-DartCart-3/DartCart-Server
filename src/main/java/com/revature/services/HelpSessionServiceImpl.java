//package com.revature.services;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
////import com.revature.models.HelpSession;
//import com.revature.models.SessionStatus;
//import com.revature.models.User;
//import com.revature.repositories.HelpSessionRepo;
//import com.revature.repositories.UserRepo;
//
//@Service
//public class HelpSessionServiceImpl implements HelpSessionService {
//
//	@Autowired
//	HelpSessionRepo helpSessionRepo;
//
//	@Autowired
//	UserRepo userRepo;
//
//
//	//Create new help session with the user, and set the status to UNASSIGNED
//	@Override
//	public void createSession(int userId) {
//		Optional user = userRepo.findById(userId);
//		if (user.isPresent()) {
//			User client = (User) user.get();
//
//			System.out.println(client);
//
//			HelpSession newHelpSession = null;
//			newHelpSession.setClient(client);
//			newHelpSession.setSessionStatus(SessionStatus.UNASSIGNED);
//
//			helpSessionRepo.save(newHelpSession);
//		}
//
//	}
//
//	@Override
//	public Optional<HelpSession> getSessionById(int id) {
//		return helpSessionRepo.findById(id);
//	}
//
//	@Override
//	public Optional<HelpSession> getSessionByUserId(int id) {
//		return helpSessionRepo.findByUser(id);
//	}
//
//	@Override
//	public Optional<HelpSession> getSessionByTechId(int id) {
//		return helpSessionRepo.findByTech(id);
//	}
//
//	@Override
//	public List<HelpSession> getAllBySessionStatus(SessionStatus sessionStatus) {
//		return helpSessionRepo.findAllBySessionStatus(sessionStatus);
//	}
//
//}
