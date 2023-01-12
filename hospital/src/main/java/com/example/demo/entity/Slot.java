package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Slot")
public class Slot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long slotId;

	@Column(name = "Start")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime slotStartTime;

	@Column(name = "End")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime slotEndTime;

	@Column(name = "Date")
	private LocalDate slotDate;

	@Column(name = "Slot_Status")
	private String status;

	@Column(name = "Price")
	private double price;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Facility")
	private Facility facility;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;

	public long getSlotId() {
		return slotId;
	}

	public void setSlotId(long slotId) {
		this.slotId = slotId;
	}

	public LocalDateTime getSlotStartTime() {
		return slotStartTime;
	}

	public void setSlotStartTime(LocalDateTime slotStartTime) {
		this.slotStartTime = slotStartTime;
	}

	public LocalDateTime getSlotEndTime() {
		return slotEndTime;
	}

	public void setSlotEndTime(LocalDateTime slotEndTime) {
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

	public Slot() {

	}

	public Slot(long slotId, LocalDateTime slotStartTime, LocalDateTime slotEndTime, LocalDate slotDate, String status,
			double price, Facility facility, Doctor doctor, Hospital hospital) {
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
	}

	@Override
	public String toString() {
		return "Slot [slotId=" + slotId + ", slotStartTime=" + slotStartTime + ", slotEndTime=" + slotEndTime
				+ ", slotDate=" + slotDate + ", status=" + status + ", price=" + price + ", facility=" + facility
				+ ", doctor=" + doctor + ", hospital=" + hospital + "]";
	}

}
