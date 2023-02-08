package com.example.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

 

@Table(name = "userDetails")
@Entity(name = "userDetails")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@Column(name = "User_Name")
	private String name;

	@Column(name = "Role")
	private String role; // admin patient doctor

	@Column(name = "Age")
	private int age;

	@Column(name = "Email", unique = true)
	private String email;

	@Column(name = "password")
	private String password;

	@ManyToOne
	@JoinColumn(name = "ID")
	private Hospital hospital;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	
	public User() {

	}

}
