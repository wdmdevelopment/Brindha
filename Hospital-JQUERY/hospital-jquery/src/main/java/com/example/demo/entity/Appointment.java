package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long appointmentId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hospital")
	private Hospital hospital;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "appointment")
	private Doctor doctor;

	@Column
	private Patient patient;

	@OneToMany(mappedBy = "apppointment", cascade = CascadeType.ALL)
	private List<Service> service;

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Service> getService() {
		return service;
	}

	public void setService(List<Service> service) {
		this.service = service;
	}

	public Appointment() {
	}

	public Appointment(long appointmentId, Hospital hospital, Doctor doctor, Patient patient, List<Service> service) {
		super();
		this.appointmentId = appointmentId;
		this.hospital = hospital;
		this.doctor = doctor;
		this.patient = patient;
		this.service = service;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", hospital=" + hospital + ", doctor=" + doctor
				+ ", patient=" + patient + ", service=" + service + "]";
	}

}
