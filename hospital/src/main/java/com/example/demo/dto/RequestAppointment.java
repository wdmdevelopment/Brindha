package com.example.demo.dto;

import java.time.LocalDateTime;


import javax.validation.constraints.NotBlank;

public class RequestAppointment {

	@NotBlank
	private LocalDateTime slotStartTime;

	private double price;

	private char admitStatus;

	public LocalDateTime getSlotStartTime() {
		return slotStartTime;
	}

	public void setSlotStartTime(LocalDateTime slotStartTime) {
		this.slotStartTime = slotStartTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public char getAdmitStatus() {
		return admitStatus;
	}

	public void setAdmitStatus(char admitStatus) {
		this.admitStatus = admitStatus;
	}

}
