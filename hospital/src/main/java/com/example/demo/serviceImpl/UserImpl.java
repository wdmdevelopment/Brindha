package com.example.demo.serviceImpl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestUser;
import com.example.demo.entity.Hospital;
import com.example.demo.entity.User;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	public List<User> getAllUser() {

		return userRepo.findAll();
	}

	public User getUserById(long id) {

		try {
			User user = userRepo.findById(id).get();
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new IdNotFoundException("User id not found" + ex);
		}

	}

	public User saveUser(RequestUser reqUser) {

		Hospital hos1 = new Hospital();
		hos1.setHospitalName(reqUser.getHospitalName());

		Hospital hos2 = new Hospital();
		hos2.setContactNum(reqUser.getContactNum());

		List<Hospital> hos = new ArrayList<Hospital>();
		hos.add(hos1);
		hos.add(hos2);

		User user = new User();
		user.setAge(reqUser.getAge());
		user.setDob(reqUser.getDob());
		user.setHospital((Hospital) hos);

		return userRepo.save(user);
	}

	public User updateUser(User user) {

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

}