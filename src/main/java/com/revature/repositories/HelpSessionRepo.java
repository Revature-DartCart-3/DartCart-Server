package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.revature.models.HelpSession;
import com.revature.models.SessionStatus;


public interface HelpSessionRepo extends CrudRepository<HelpSession, Integer> {
	Optional<HelpSession> findByClient(int id);
	//Optional<HelpSession> findByTech(int id);
	List<HelpSession> findAllBySessionStatus(SessionStatus status);
}
