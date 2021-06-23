package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exception.UserExistException;
import com.stacksimplify.restservices.exception.UserNotFoundException;
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
	public User createUser(User user) throws UserExistException{
		User existUser = userRepository.findByUsername(user.getUsername());
		if(existUser != null) {
			throw new UserExistException("User already exist!");
		}
		return userRepository.save(user);
		
	}

	// getUserMethodByID
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		if(userRepository.findById(id).isPresent()) {
			return userRepository.findById(id);
		}
		else {
			throw new UserNotFoundException("User not Found in User Repository");
		}
	}

	// updateUserById
	public User updateUserById (Long id, User user) throws UserNotFoundException {
		if(!userRepository.findById(id).isPresent()) {
			throw new UserNotFoundException("User not found in Repository");
		}
			
		user.setId(id);
		return userRepository.save(user);
		
	}

	// deleteUserById
	public void deleteUserById(Long id) throws UserNotFoundException {
		
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not Found in Repo. Please provide correct ID");
		}
	
	}
	
	//get User by Username
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}