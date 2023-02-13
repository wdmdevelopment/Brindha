package com.file.upload.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "user")
@Entity(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	@Column(name = "User_Name")
	private String name;
	
	@Column(name = "Email", unique = true)
	private String email;

	@Column(name = "Password")
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Facebook", referencedColumnName = "fb_id")
	private Facebook facebook;

	@Column(name = "profile")
	private String profileImg;
	
	@Column(name = "language")
	private String userLevel;

}
