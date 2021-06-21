package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.User;
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
	public User creatUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping ("/users/{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	//updateUserById
	@PutMapping("/users/{id}")
	public User updateUserById (@PathVariable ("id") Long id, @RequestBody User user) {
		return userService.updateUserById(id, user);
	}
	
	//deleteUserById
	@DeleteMapping("/users/{id}")
	public void deleteUserById (@PathVariable Long id){
		userService.deleteUserById(id);
	}
	
	//find iser by Username
	@GetMapping("/users/byusername/{username}")
	public User findUserByUsername(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}
}


