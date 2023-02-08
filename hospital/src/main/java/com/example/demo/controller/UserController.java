package com.example.demo.controller;


import java.util.List;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RequestLogin;
import com.example.demo.dto.RequestUser;
import com.example.demo.entity.User;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/user")
	public List<UserResponse> getAllUser() {
		logger.info("Get all users ");
		List<User> allUser = userService.getAllUser();

		return allUser.stream().map(e -> new UserResponse(e.getName(), e.getRole(), e.getContactNum(), e.getEmail()))
				.collect(Collectors.toList());

	}

	@GetMapping("/user/{id}")
	public ResponseEntity<RequestUser> getUserById(@PathVariable("id") long id) {
		logger.info("User details get by ID ");
		
		RequestUser user = userService.getUserById(id);

		if (user == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(user);
		}
	}

	@PostMapping("/user/add")
	public ResponseEntity<User> saveUser(@Valid @RequestBody RequestUser user) {
		logger.info("User created");

		return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);

	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long userId, @RequestBody RequestUser reqUser) {
		logger.info("Updated User details ");
		
		User user = userService.updateUser(userId, reqUser);
		
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(user);

	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
		logger.info("Deleting user ");
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/user/role/{role}")
	public ResponseEntity<List<User>> getUserByRole(@PathVariable String role) {

		return new ResponseEntity<>(userService.UserByRole(role), HttpStatus.OK);
	}
	

	@PostMapping("/login")
	public ResponseEntity<?> login(RequestLogin reqLogin) {
		logger.info("login User");
		return new ResponseEntity<>(userService.login(reqLogin.getEmail(), reqLogin.getPassword()), HttpStatus.OK);
}
}
