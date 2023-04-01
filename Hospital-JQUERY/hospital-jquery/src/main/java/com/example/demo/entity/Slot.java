package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Slot")
public class Slot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long slotId;

	@Column(name = "Start")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private LocalTime slotStartTime;

	@Column(name = "End")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	private LocalTime slotEndTime;

	@Column(name = "Date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate slotDate;

	@Column(name = "Slot_Status")
	private String status;

	@Column(name = "Price")
	private double price;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Facility")
	private Facility facility;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	@ManyToOne
	@JoinColumn(name = "hospital_id")
	@JsonIgnore
	private Hospital hospital;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "User")
	private User user;

	public long getSlotId() {
		return slotId;
	}

	public void setSlotId(long slotId) {
		this.slotId = slotId;
	}

	public LocalTime getSlotStartTime() {
		return slotStartTime;
	}

	public void setSlotStartTime(LocalTime slotStartTime) {
		this.slotStartTime = slotStartTime;
	}

	public LocalTime getSlotEndTime() {
		return slotEndTime;
	}

	public void setSlotEndTime(LocalTime slotEndTime) {
		this.slotEndTime = slotEndTime;
	}

	public LocalDate getSlotDate() {
		return slotDate;
	}

	public void setSlotDate(LocalDate slotDate) {
		this.slotDate = slotDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Slot() {

	}

	@Override
	public String toString() {
		return "Slot [slotId=" + slotId + ", slotStartTime=" + slotStartTime + ", slotEndTime=" + slotEndTime
				+ ", slotDate=" + slotDate + ", status=" + status + ", price=" + price + ", facility=" + facility
				+ ", doctor=" + doctor + ", hospital=" + hospital + ", user=" + user + "]";
	}

	public Slot(long slotId, LocalTime slotStartTime, LocalTime slotEndTime, LocalDate slotDate, String status,
			double price, Facility facility, Doctor doctor, Hospital hospital, User user) {
		super();
		this.slotId = slotId;
		this.slotStartTime = slotStartTime;
		this.slotEndTime = slotEndTime;
		this.slotDate = slotDate;
		this.status = status;
		this.price = price;
		this.facility = facility;
		this.doctor = doctor;
		this.hospital = hospital;
		this.user = user;
	}

}
