package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Hospital")
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long hospitalId;

	@Column(name = "Hospital_Name")
	private String hospitalName;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Address")
	private Address address;

	@Column(name = "PhNo")
	private String contactNum;

	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	private List<Doctor> doctors;

	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	private List<Service> service;

	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	private List<Appointment> appointment;

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

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public List<Service> getService() {
		return service;
	}

	public void setService(List<Service> service) {
		this.service = service;
	}

	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	public Hospital() {
	}

	public Hospital(long hospitalId, String hospitalName, Address address, String contactNum, List<Doctor> doctors,
			List<Service> service, List<Appointment> appointment) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.address = address;
		this.contactNum = contactNum;
		this.doctors = doctors;
		this.service = service;
		this.appointment = appointment;
	}

	@Override
	public String toString() {
		return "Hospital [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", address=" + address
				+ ", contactNum=" + contactNum + ", doctors=" + doctors + ", service=" + service + ", appointment="
				+ appointment + "]";
	}

}
