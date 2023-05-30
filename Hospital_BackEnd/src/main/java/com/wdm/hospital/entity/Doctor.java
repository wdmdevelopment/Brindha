package com.wdm.hospital.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long doctorId;

	@Column(name = "Specialist")
	private String doctorSpecialist;

	@ManyToOne
	@JoinColumn(name = "hospital_id")
	@JsonIgnoreProperties("doctors")
	private Hospital hospital;

	@ManyToMany
	@JoinTable(name = "Doctor_Patient_Map", joinColumns = { @JoinColumn(name = "doctor_Id") }, inverseJoinColumns = {
			@JoinColumn(name = "patient_Id") })
	private List<Patient> patientList;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Address", referencedColumnName = "ID")
	private Address doctorAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "User", referencedColumnName = "ID")
	private User user;

	@OneToMany(mappedBy = "doctor", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Slot> slot;

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorSpecialist() {
		return doctorSpecialist;
	}

	public void setDoctorSpecialist(String doctorSpecialist) {
		this.doctorSpecialist = doctorSpecialist;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public List<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}

	public Address getDoctorAddress() {
		return doctorAddress;
	}

	public void setDoctorAddress(Address doctorAddress) {
		this.doctorAddress = doctorAddress;
	}

	public List<Slot> getSlot() {
		return slot;
	}

	public void setSlot(List<Slot> slot) {
		this.slot = slot;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Doctor() {

	}

	public Doctor(long doctorId, String doctorSpecialist, Hospital hospital, List<Patient> patientList,
			Address doctorAddress, User user, List<Slot> slot) {
		super();
		this.doctorId = doctorId;
		this.doctorSpecialist = doctorSpecialist;
		this.hospital = hospital;
		this.patientList = patientList;
		this.doctorAddress = doctorAddress;
		this.user = user;
		this.slot = slot;
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorSpecialist=" + doctorSpecialist + ", hospital=" + hospital
				+ ", patientList=" + patientList + ", doctorAddress=" + doctorAddress + ", user=" + user + ", slot="
				+ slot + "]";
	}

}
