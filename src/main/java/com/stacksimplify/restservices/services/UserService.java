package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exception.UserExistsException;
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
	public User createUser(User user) throws UserExistsException {
		User existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser != null) {
			throw new UserExistsException("User already exists in user repository");

		}
		return userRepository.save(user);
	}

	// getUserById
	public Optional<User> getUserId(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user;
		} else {
			throw new UserNotFoundException("User not found in user repository");
		}

	}

	// updateUserById
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found in user repository");
		}
		user.setId(id);
		return userRepository.save(user);

	}

	// deleteUserById
	public void deleteUserById(Long id) {
		if (!userRepository.findById(id).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"User not found in user repository, Enter correct id");
		}
		userRepository.deleteById(id);

	}

	// getUserByUsername
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);

	}

}
