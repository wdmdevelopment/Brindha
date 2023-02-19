package com.example.demo.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Appointment_Booking")
public class AppointmentBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long appointmentId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Slot")
	private Slot slot;

//	@OneToMany(mappedBy = "AppointmentBooking", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "Hospital", referencedColumnName = "ID")
//	private Hospital hospital;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Facility")
	private Facility facility;
	
	@Column(name = "DateTime")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime bookTime;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "Patient")
//	private Patient patient;

//	@Column(name = "Status")
//	private String bookingStatus;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "User", referencedColumnName = "ID")
	private User user;

//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name = "Address", referencedColumnName = "ID")
//	private Address address;

	
	
	public AppointmentBooking() {

	}

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

//	public Hospital getHospital() {
//		return hospital;
//	}
//
//	public void setHospital(Hospital hospital) {
//		this.hospital = hospital;
//	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public LocalDateTime getBookTime() {
		return bookTime;
	}

	public void setBookTime(LocalDateTime bookTime) {
		this.bookTime = bookTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AppointmentBooking(long appointmentId, Slot slot, Facility facility,
			LocalDateTime bookTime, User user) {
		super();
		this.appointmentId = appointmentId;
		this.slot = slot;
//		this.hospital = hospital;
		this.facility = facility;
		this.bookTime = bookTime;
		this.user = user;
	}

	@Override
	public String toString() {
		return "AppointmentBooking [appointmentId=" + appointmentId + ", slot=" + slot + ",, facility=" + facility + ", bookTime=" + bookTime + ", user=" + user + "]";
	}

	
	

}
