package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

public class RequestSlot {
	@NotBlank
	private LocalDateTime slotStartTime;
	@NotBlank
	private LocalDateTime slotEndTime;
	
	private LocalDate slotDate;
	
	private String status;
	@NotBlank
	private double price;

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

}
