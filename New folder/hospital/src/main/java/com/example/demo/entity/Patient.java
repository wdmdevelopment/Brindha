package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long patientId;

	@Column(name = "Contact_No")
	private String patientPhNo;

	@JsonIgnore
	@ManyToMany(mappedBy = "patientList")
	private Set<Doctor> doctorList = new HashSet<>();

	@Column(name = "Name")
	private String name;

	@Column(name = "AdmitStatus")
	private char admitStatus;

	@Column(name = "Age")
	private int age;

	@OneToOne
	@JoinColumn(name="Address", referencedColumnName = "ID")
	private Address patientAddress;

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

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

	public char getadmitStatus() {
		return admitStatus;
	}

	public void setadmitStatus(char admitStatus) {
		this.admitStatus = admitStatus;
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

	public Patient() {
	}

	public Patient(long patientId, String patientPhNo, Set<Doctor> doctorList, String name, char admitStatus, int age,
			Address patientAddress) {
		super();
		this.patientId = patientId;
		this.patientPhNo = patientPhNo;
		this.doctorList = doctorList;
		this.name = name;
		this.admitStatus = admitStatus;
		this.age = age;
		this.patientAddress = patientAddress;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientPhNo=" + patientPhNo + ", doctorList=" + doctorList + ", name="
				+ name + ", admitStatus=" + admitStatus + ", age=" + age + ", patientAddress=" + patientAddress + "]";
	}

}
