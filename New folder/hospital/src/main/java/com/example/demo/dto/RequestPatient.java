package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.entity.Address;
import com.example.demo.entity.Doctor;

public class RequestPatient {

	private String patientPhNo;

	private Set<Doctor> doctorList = new HashSet<>();

	private String name;

	private char status;

	private int age;

	private Address patientAddress;

	public String getPatientPhNo() {
		return patientPhNo;
	}

	public void setPatientPhNo(String patientPhNo) {
		this.patientPhNo = patientPhNo;
	}

	public Set<Doctor> getdoctorList() {
		return doctorList;
	}

	public void setdoctorList(Set<Doctor> doctorList) {
		this.doctorList = doctorList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(Address patientAddress) {
		this.patientAddress = patientAddress;
	}
}
