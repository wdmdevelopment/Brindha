package com.example.demo.entity;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Service")
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long serviceId;

	@Column(name = "ServiceName")
	private String serviceName;

	@Column(name = "Fees")
	private double fees;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "appointment")
	private Appointment appointment;

	public long getServiceId() {
		return serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Service() {

	}

	public Service(long serviceId, String serviceName, double fees, Hospital hospital, Appointment appointment) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.fees = fees;
		this.hospital = hospital;
		this.appointment = appointment;
	}

	@Override
	public String toString() {
		return "Service [serviceId=" + serviceId + ", serviceName=" + serviceName + ", fees=" + fees + ", hospital="
				+ hospital + ", appointment=" + appointment + "]";
	}

}
