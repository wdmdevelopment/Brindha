package com.wdm.hospital.dto;

import java.time.LocalDate;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;


public class RequestSlot {
	
	private long userId;
	
	@NotNull
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
//	@JsonDeserialize(using = LocalTimeDeserializer.class)
//	@JsonSerialize(using = LocalTimeSerializer.class)
	private String slotStartTime;
	
	@NotNull
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
//	@JsonDeserialize(using = LocalTimeDeserializer.class)
//	@JsonSerialize(using = LocalTimeSerializer.class)
	private String slotEndTime;
	
	@NotNull
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate slotDate;

	@NotNull
	
	private String status;
	
	@NotNull
	private String facilityName;

	@NotNull
	private Double price;
	
	@NotNull
	private Long hospitalId;
	
	 

	public String getSlotStartTime() {
		return slotStartTime;
	}

	public void setSlotStartTime(String slotStartTime) {
		this.slotStartTime = slotStartTime;
	}

	public String getSlotEndTime() {
		return slotEndTime;
	}

	public void setSlotEndTime(String slotEndTime) {
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

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}

	
}
