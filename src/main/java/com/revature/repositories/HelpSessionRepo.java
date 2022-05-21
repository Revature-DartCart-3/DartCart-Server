package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.revature.models.HelpSession;
import com.revature.models.SessionStatus;

//HUNTER'S REPOSITORY
//public interface HelpSessionRepo extends CrudRepository<HelpSession, Integer> {
//	Optional<HelpSession> findByUser(Integer id);
//	Optional<HelpSession> findByTech(Integer id);
//	List<HelpSession> findAllBySessionStatus(SessionStatus status);

	public interface HelpSessionRepo extends JpaRepository<HelpSession, Integer> {
		Optional<HelpSession> findByUser(Integer id);
		Optional<HelpSession> findByTech(Integer id);
		List<HelpSession> findAllBySessionStatus(SessionStatus status);
	
}
