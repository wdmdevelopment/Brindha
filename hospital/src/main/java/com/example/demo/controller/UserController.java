package com.example.demo.controller;

import java.util.List;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RequestUser;
import com.example.demo.entity.User;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/user")
	public List<UserResponse> getAllUser() {
		logger.info("Get all users " + getAllUser());
		List<User> allUser = userService.getAllUser();

		return allUser.stream().map(e -> new UserResponse(e.getUserName(), e.getRole(), e.getEmail(), e.getContactNum()))
				.collect(Collectors.toList());

	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		logger.info("User details get by ID " + getUserById(id));
		User user = userService.getUserById(id);

		if (user == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(user);
		}
	}

	@PostMapping("/user")
	public ResponseEntity<User> saveUser(@Valid @RequestBody RequestUser user) {
		logger.info("User created");

		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);

	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		logger.info("Updated User details " + updateUser(user));
		return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);

	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
		logger.info("Deleting user " + deleteUser(id));
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
