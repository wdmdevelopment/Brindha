package com.wdm.hospital.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RequestHospital {
	
	@NotNull
	private long adminId;
	
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
	
	@NotBlank
	private String facilityName;
	
	@NotNull
	private long addressId;
	
	@NotBlank
	private String[] facilityId;
	

 

	public String[] getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String[] facilityId) {
		this.facilityId = facilityId;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

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

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
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

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	
	
}
