package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.revature.models.HelpSession;
import com.revature.models.SessionStatus;

public interface HelpSessionRepo extends CrudRepository<HelpSession, Integer> {
	Optional<HelpSession> findByUser(int id);
	Optional<HelpSession> findByTech(int id);
	List<HelpSession> findAllBySessionStatus(SessionStatus status);
	
}
