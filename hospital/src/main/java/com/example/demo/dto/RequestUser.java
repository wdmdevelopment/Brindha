package com.example.demo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RequestUser {
	@NotNull
	private String name;
	@NotNull
	private int age;
	@NotNull
	private String role;
	@NotNull
	private String email;
	@NotNull
	@Size(min = 8, max = 20)
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
