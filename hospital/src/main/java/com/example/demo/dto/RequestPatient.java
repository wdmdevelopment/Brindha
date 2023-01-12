package com.example.demo.dto;


import javax.validation.constraints.NotBlank;


public class RequestPatient {

	private String patientPhNo;

	@NotBlank
	private String name;

	private char admitStatus;

	private String doctorName;
	
	private int age;
	
	private String contactNum;
	
	private String doctorSpecialist;

	public String getPatientPhNo() {
		return patientPhNo;
	}

	public void setPatientPhNo(String patientPhNo) {
		this.patientPhNo = patientPhNo;
	}

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

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getDoctorSpecialist() {
		return doctorSpecialist;
	}

	public void setDoctorSpecialist(String doctorSpecialist) {
		this.doctorSpecialist = doctorSpecialist;
	}
	
	
}
