package com.example.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long doctorId;

	@Column(name = "Name")
	private String doctorName;

	@Column(name = "Specialist")
	private String doctorSpecialist;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hospital")
	@JsonIgnoreProperties("doctors")
	private Hospital hospital;

	@ManyToMany
	@JoinTable(name = "Doctor_Patient_Map", joinColumns = { @JoinColumn(name = "doctor_Id") }, inverseJoinColumns = {
			@JoinColumn(name = "patient_Id") })
	private Set<Patient> patientList = new HashSet<>();

	
	@OneToOne
	@JoinColumn(name = "Address", referencedColumnName = "ID")
	private Address doctorAddress;

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Set<Patient> getpatientList() {
		return patientList;
	}

	public void setpatientList(Set<Patient> patientList) {
		this.patientList = patientList;
	}

	public String getDoctorSpecialist() {
		return doctorSpecialist;
	}

	public void setDoctorSpecialist(String doctorSpecialist) {
		this.doctorSpecialist = doctorSpecialist;
	}

	public Address getDoctorAddress() {
		return doctorAddress;
	}

	public void setDoctorAddress(Address doctorAddress) {
		this.doctorAddress = doctorAddress;
	}

	public Doctor() {

	}

	public Doctor(long doctorId, String doctorName, String doctorSpecialist, Hospital hospital,
			Set<Patient> patientList, Address doctorAddress) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.doctorSpecialist = doctorSpecialist;
		this.hospital = hospital;
		this.patientList = patientList;
		this.doctorAddress = doctorAddress;
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", doctorSpecialist=" + doctorSpecialist
				+ ", hospital=" + hospital + ", patientList=" + patientList + ", doctorAddress=" + doctorAddress + "]";
	}

}
