package com.example.demo.response;


public class UserResponse {

	private String userName;
	
	private String role;
	
	private String contactNum;
	
	private String email;
	

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

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public UserResponse(String userName, String role, String contactNum, String email) {
		super();
		this.userName = userName;
		this.role = role;
		this.contactNum = contactNum;
		this.email = email;
	}
	
	
	
}
