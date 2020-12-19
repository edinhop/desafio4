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
import com.desafio.agenda.domain.model.User;
import com.desafio.agenda.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API REST Agenda")
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users")
	@ApiOperation(value="Retorna uma lista de usuários")
	public List<User> userList( ) {
		return userRepository.findAll();
		
	}
	
	@GetMapping("/user/{id}")
	@ApiOperation(value="Retorna um unico usuário")
	public User userId(@PathVariable(value="id")long id) {
		return userRepository.findById(id);
		
	}
	
	@PostMapping("/user")
	@ApiOperation(value="Salva um usuário")
	public User saveUser(@RequestBody User user) {
		return userRepository.save(user);
		
	}
	
	@DeleteMapping("/user")
	@ApiOperation(value="Deleta usuário")
	public void deleteUser(@RequestBody User user) {
		userRepository.delete(user);
		
	}
	
	@PutMapping("/user")
	@ApiOperation(value="Atualiza um usuário")
	public User updateUser(@RequestBody User user) {
		return userRepository.save(user);
		
	}
}
