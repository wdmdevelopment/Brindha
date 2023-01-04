package com.example.demo.dto;

import java.util.List;

import com.example.demo.entity.Address;
import com.example.demo.entity.Doctor;

public class RequestHospital {
	

	private String hospitalName;

	private Address address;

	private String contactNum;

	private List<Doctor> doctors;

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public List<Doctor> getdoctors() {
		return doctors;
	}

	public void setdoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public RequestHospital() {

	}

	public RequestHospital(long hospitalId, String hospitalName, Address address, String contactNum, List<Doctor> doctors) {
		super();
		this.hospitalName = hospitalName;
		this.address = address;
		this.contactNum = contactNum;
		this.doctors = doctors;
	}

	public String toString() {
		return "RequestHospital [hospitalName=" + hospitalName + ", address=" + address
				+ ", contactNum=" + contactNum + ", doctors=" + doctors + "]";
	}
}
