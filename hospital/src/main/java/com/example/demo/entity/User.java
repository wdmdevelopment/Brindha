package com.example.demo.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@Column(name = "User_Name")
	@Size(min = 6)
	private String userName;

	@Column(name = "Role")
	private String role;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID")
	private Hospital hospital;

	@Column(name = "DOB")
	private LocalDate dob;

	@Column(name = "Age")
	private int age;

	@Column(name = "Email", unique = true)
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "Mobile_Num")
	private String contactNum;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate date) {
		this.dob = date;
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

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public User() {

	}

	public User(long userId, @Size(min = 6) String userName, String role, Hospital hospital, LocalDate dob, int age,
			String email, String password, String contactNum) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.role = role;
		this.hospital = hospital;
		this.dob = dob;
		this.age = age;
		this.email = email;
		this.password = password;
		this.contactNum = contactNum;
	}

}
