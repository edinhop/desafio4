package com.desafio.agenda.repository;

import com.desafio.agenda.domain.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.agenda.domain.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
	User findById(long id);

	@Query("select u from User u where u.email=:email and u.password=:password")
	User findContactByUserId(String email, String password);
}
