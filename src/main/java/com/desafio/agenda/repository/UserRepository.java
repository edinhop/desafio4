package com.desafio.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.agenda.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findById(long id);
	
}
