package com.stacksimplify.restservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;

//service
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
}
