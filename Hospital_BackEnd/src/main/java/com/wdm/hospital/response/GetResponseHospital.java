package com.wdm.hospital.response;

import java.util.List;

import com.wdm.hospital.entity.Facility;

public class GetResponseHospital {
	
	private long hospitalId;	
	  
	
	private String hospitalName;
	
	private String contactNum;
	
	private long addressId;
	
	private String city;
	
	private String district;
	
	private String state;
	
	private String country;
	
	private String pincode;
 
	
	private List<Facility> facilityName;
	
	private long imageId;
	
	private byte[] data;

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

 

	public List<Facility> getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(List<Facility> facilityName) {
		this.facilityName = facilityName;
	}

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public GetResponseHospital(long hospitalId, String hospitalName, String contactNum, long addressId, String city,
			String district, String state, String country, String pincode,   List<Facility> facilityName,
			long imageId, byte[] data) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.contactNum = contactNum;
		this.addressId = addressId;
		this.city = city;
		this.district = district;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	 
		this.facilityName = facilityName;
		this.imageId = imageId;
		this.data = data;
	}
	
	
	
	
	
	

}
