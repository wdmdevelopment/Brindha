package com.file.upload.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "facebook")
@Entity(name = "facebook")
public class Facebook {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long fbId;
	
	@Column(name = "f_Name")
	private String fName;
	
	@Column(name = "l_Name")
	private String lName;
	
	@Column(name = "Email", unique = true)
	private String email;

	@Column(name = "Password")
	private String password;
	
	@Column(name = "Date_Of_Birth")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate dob;
}
