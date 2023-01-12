package com.example.demo.entity;

import java.util.List;

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

	@JsonIgnore
	@ManyToMany(mappedBy = "patientList")
	private List<Doctor> doctorsList;

	@Column(name = "AdmitStatus")
	private char admitStatus;

	@OneToOne
	@JoinColumn(name = "Address", referencedColumnName = "ID")
	private Address patientAddress;

	@OneToOne
	@JoinColumn(name = "User", referencedColumnName = "ID")
	private User user;

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public List<Doctor> getDoctorsList() {
		return doctorsList;
	}

	public void setDoctorsList(List<Doctor> doctorsList) {
		this.doctorsList = doctorsList;
	}

	public char getAdmitStatus() {
		return admitStatus;
	}

	public void setAdmitStatus(char admitStatus) {
		this.admitStatus = admitStatus;
	}

	public Address getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(Address patientAddress) {
		this.patientAddress = patientAddress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Patient() {

	}

	public Patient(long patientId,List<Doctor> doctorsList, char admitStatus,
			Address patientAddress, User user) {
		super();
		this.patientId = patientId;
		this.doctorsList = doctorsList;
		this.admitStatus = admitStatus;
		this.patientAddress = patientAddress;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", doctorsList=" + doctorsList
				+ ", admitStatus=" + admitStatus + ", patientAddress=" + patientAddress + ", user=" + user + "]";
	}

}
