package com.example.demo.serviceImpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestUser;
import com.example.demo.entity.Hospital;
import com.example.demo.entity.User;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.HospitalRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired

	public List<User> getAllUser() {

		return userRepo.findAll();
	}

	public RequestUser getUserById(long id) {

		User user;
		try {
			user = userRepo.findById(id).get();
		} catch (Exception e) {
			throw new IdNotFoundException(e.getMessage());
		}

		RequestUser reqUser = new RequestUser();

		reqUser.setName(user.getName());
		reqUser.setAge(user.getAge());
		reqUser.setEmail(user.getEmail());
		reqUser.setPassword(user.getPassword());
		reqUser.setRole(user.getRole());

		return reqUser;

	}

	public User saveUser(RequestUser reqUser) {

		
		User user = new User();

		user.setName(reqUser.getName());
		user.setAge(reqUser.getAge());
		user.setEmail(reqUser.getEmail());
		user.setPassword(reqUser.getPassword());
		user.setRole(reqUser.getRole());
		
		
		
		return userRepo.save(user);
	}

	public User updateUser(long userId, RequestUser reqUser) {

		
		User user = userRepo.findById(userId).orElseThrow(()-> new IdNotFoundException("id not found"));

		user.setName(reqUser.getName());
		user.setAge(reqUser.getAge());
		user.setEmail(reqUser.getEmail());
		user.setPassword(reqUser.getPassword());
		user.setRole(reqUser.getRole());
		
		
		
		return userRepo.save(user);
	}

	public void deleteUser(long id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			userRepo.deleteById(id);
		} else {
			throw new IdNotFoundException("Given ID: " + id + "  not found with any user");

		}

	}

	public List<User> UserByRole(String role) {

		return userRepo.getUserByRole(role);
	}

	public User login(String email, String password) {
		User user = userRepo.findByEmailAndPassword(email, password);
		
		if(user.getEmail().contains(email) && user.getPassword().contains(password)) {
			return user;
		}else {
			
			throw new IdNotFoundException("Invalid Information");
		}
		
		
	}
}