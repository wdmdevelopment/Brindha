package com.example.demo.entity;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Appointment_Booking")
public class AppointmentBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long appointmentId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Slot")
	private Slot slot;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Patient")
	private Patient patient;

	@Column(name = "Status")
	private String bookingStatus;

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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public AppointmentBooking() {

	}

	public AppointmentBooking(long appointmentId, Slot slot, Patient patient, String bookingStatus) {
		super();
		this.appointmentId = appointmentId;
		this.slot = slot;
		this.patient = patient;
		this.bookingStatus = bookingStatus;
	}

	@Override
	public String toString() {
		return "AppointmentBooking [appointmentId=" + appointmentId + ", slot=" + slot + ", patient=" + patient
				+ ", bookingStatus=" + bookingStatus + "]";
	}

}
