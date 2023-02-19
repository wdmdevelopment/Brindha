package com.example.demo.response;

import javax.validation.constraints.NotNull;

public class HospitalResponse {
	
	private long userId;	
	private long hospitalId;
	private String hospitalName;
	private String contactNum;
	private long addressId;
	private String city;
	private String district;
	private String state;
	private String country;
	private String pincode;
	private long facilityId;
	private String facilityName;
	private byte[] data;
	private long imageId;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
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
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
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
	public long getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(long facilityId) {
		this.facilityId = facilityId;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public long getImageId() {
		return imageId;
	}
	public void setImageId(long imageId) {
		this.imageId = imageId;
	}
	public HospitalResponse(long userId, long hospitalId, String hospitalName, String contactNum, long addressId,
			String city, String district, String state, String country, String pincode, long facilityId,
			String facilityName, byte[] data, long imageId) {
		super();
		this.userId = userId;
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.contactNum = contactNum;
		this.addressId = addressId;
		this.city = city;
		this.district = district;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.facilityId = facilityId;
		this.facilityName = facilityName;
		this.data = data;
		this.imageId = imageId;
	}



}
