package com.wdm.hospital.dto;



import javax.validation.constraints.NotBlank;



public class RequestPatient {

	@NotBlank
	private String name;

	private char admitStatus;

	private int age;
	
	private String role;
	private String email;
	
	private String password;
	
	private String city;

	private String district;
	
	private String state;
	
	private String country;
	
	private String pincode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getAdmitStatus() {
		return admitStatus;
	}

	public void setAdmitStatus(char admitStatus) {
		this.admitStatus = admitStatus;
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

	


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
}
