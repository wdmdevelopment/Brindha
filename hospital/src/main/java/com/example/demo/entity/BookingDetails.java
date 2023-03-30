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
@Table(name = "Booking")
public class BookingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "book_id")
	private long bookingId;

//	@OneToOne
//	@JoinColumn(name = "hospital_id")
//	private Hospital hospital;

	@OneToOne
	private Slot slot;

	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "USER_ID", referencedColumnName = "user_id")
	private User user;

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}
 
//	public Hospital getHospital() {
//		return hospital;
//	}
//
//	public void setHospital(Hospital hospital) {
//		this.hospital = hospital;
//	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
