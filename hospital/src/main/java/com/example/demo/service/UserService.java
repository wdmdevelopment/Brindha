package com.example.demo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.demo.dto.RequestLogin;
import com.example.demo.dto.RequestUser;
import com.example.demo.entity.User;

@Service
public interface UserService {

	public List<User> getAllUser();
	
	public RequestUser getUserById(long id);
	
	public User saveUser(@Valid RequestUser reqEmployee);
	
	public User updateUser(long userId, RequestUser reqUser);
	
	public void deleteUser(long id);

	public List<User> UserByRole(String role);



	public User login(String email, String password);

	
}
