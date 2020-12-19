package com.desafio.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.desafio.agenda.domain.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	Contact findById(long id);
	
	@Query("select c from Contact c where c.user.id=:userId")
	List <Contact> findContactByUserId(Long userId);
}
