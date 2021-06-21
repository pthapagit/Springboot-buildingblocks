package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;

//service
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// create user method
	public User createUser(User user) {
		return userRepository.save(user);
	}

	// getUserMethodByID
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	// updateUserById
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}

	// deleteUserById
	public void deleteUserById(Long id) {
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	
	}
	
	//get User by Username
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}