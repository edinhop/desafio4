package com.desafio.agenda.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.desafio.agenda.domain.model.User;
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

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Agenda")
@CrossOrigin(origins="*")
public class ContactController {
	
	@Autowired
	ContactRepository contactRepository;
	
	@GetMapping("admin/contacts")
	@ApiOperation(value="Retorna uma lista de contatos")
	public List<Contact> contactList(){
		return contactRepository.findAll();
	}
	
	@GetMapping("/contacts")
	@ApiOperation(value="Retorna lista por id")
	public List<ContactSummary> contactList(HttpServletRequest request){
		User user = (User) request.getAttribute("user");
		List<Contact> contacts = contactRepository.findContactByUserId(user.getId());
		return contacts.stream().map(ContactSummary::new).collect(Collectors.toList());
	}
	
	@GetMapping("/contact/{id}")
	@ApiOperation(value="Retorna um unico contato")
	public Contact contactId(@PathVariable(value="id")long id) {
		return contactRepository.findById(id);
	}
	
	@PostMapping("/contact")
	@ApiOperation(value="Salva um contato")
	public Contact saveContact(@RequestBody Contact contact, HttpServletRequest request) {
		User user = (User) request.getAttribute("user");
		contact.setUser(user);
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

	private class ContactSummary {

		private Long id;
		private String name;
		private String email;
		private String cellphone;
		private Long userId;

		public ContactSummary(Contact contact) {
			this.id = contact.getId();
			this.name = contact.getName();
			this.email = contact.getEmail();
			this.cellphone = contact.getCellphone();
			this.userId = contact.getUser().getId();
		}

		public Long getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getEmail() {
			return email;
		}

		public String getCellphone() {
			return cellphone;
		}

		public Long getUserId() {
			return userId;
		}
	}
}

