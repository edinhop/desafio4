package com.desafio.agenda.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.agenda.domain.model.Contact;
import com.desafio.agenda.repository.ContactRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Agenda")
@CrossOrigin(origins="*")
public class ContactControll {
	
	@Autowired
	ContactRepository contactRepository;
	
	@GetMapping("/contacts")
	@ApiOperation(value="Retorna uma lista de contatos")
	public List<Contact> contactList(){
		return contactRepository.findAll();
	}
	
	@GetMapping("/contacts/{id}")
	@ApiOperation(value="Retorna lista por id")
	public List<Contact> contactList(Long userId){
		return contactRepository.findContactByUserId(userId);
	}
	
	@GetMapping("/contact/{id}")
	@ApiOperation(value="Retorna um unico contato")
	public Contact contactId(@PathVariable(value="id")long id) {
		return contactRepository.findById(id);
		
	}
	
	@PostMapping("/contact")
	@ApiOperation(value="Salva um contato")
	public Contact saveContact(@RequestBody Contact contact) {
		return contactRepository.save(contact);
		
	}
	
	@DeleteMapping("/contact")
	@ApiOperation(value="Deleta um contato")
	public void deleteContact(@RequestBody Contact contact) {
		contactRepository.delete(contact);
		
	}
	
	@PutMapping("/contact")
	@ApiOperation(value="Atualiza um contato")
	public Contact updateContact(@RequestBody Contact contact) {
		return contactRepository.save(contact);
		
	}
	
}

