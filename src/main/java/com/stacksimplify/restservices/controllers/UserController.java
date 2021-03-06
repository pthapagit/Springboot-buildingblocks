package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exception.UserExistException;
import com.stacksimplify.restservices.exception.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

//controller
@RestController
public class UserController {
	
	//Autowire the service
	@Autowired
	private UserService userService;
	
	//getAllUsers Method
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	//create user\
	@PostMapping ("/users")
	public ResponseEntity<Void> creatUser(@RequestBody User user, UriComponentsBuilder builder) {
		
		try {
			userService.createUser(user);
			HttpHeaders httpHeader = new HttpHeaders();
			httpHeader.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(httpHeader, HttpStatus.CREATED);
					
			
		} catch (UserExistException ex) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, ex.getMessage());
		}
	}
	
	@GetMapping ("/users/{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	//updateUserById
	@PutMapping("/users/{id}")
	public User updateUserById (@PathVariable ("id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	//deleteUserById
	@DeleteMapping("/users/{id}")
	public void deleteUserById (@PathVariable Long id) throws UserNotFoundException{
		userService.deleteUserById(id);
	}
	
	//find iser by Username
	@GetMapping("/users/byusername/{username}")
	public User findUserByUsername(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}
}


