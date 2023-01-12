package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestUser;
import com.example.demo.entity.User;

@Service
public interface UserService {

	public List<User> getAllUser();
	
	public User getUserById(long id);
	
	public User saveUser(@Valid RequestUser reqEmployee);
	
	public User updateUser(User user);
	
	public void deleteUser(long id);

	
}
