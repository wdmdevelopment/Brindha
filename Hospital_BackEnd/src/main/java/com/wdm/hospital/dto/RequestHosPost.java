package com.wdm.hospital.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RequestHosPost {
	
	
	private long userId;
	
	@NotBlank
	private String hospitalName;
	@NotBlank
	private String contactNum;
	@NotBlank
	private String city;
	@NotBlank
	private String district;
	
	@NotBlank
	private String state;
	
	@NotBlank
	private String country;
	
	@NotBlank
	private String pincode;
	
	 @NotNull
	private String[] facility;
	
	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
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

	 
	
	
 

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String[] getFacility() {
		return facility;
	}

	public void setFacility(String[] facility) {
		this.facility = facility;
	}

 

	 
	
	

}
