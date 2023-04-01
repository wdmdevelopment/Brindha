package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Facility")
public class Facility {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "FacilityName")
	private String facilityName;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}



	public Facility() {
		
	}
	
	public Facility(long id, String facilityName, double fees, Hospital hospital, AppointmentBooking appointment) {
		super();
		this.id = id;
		this.facilityName = facilityName;
	}

	@Override
	public String toString() {
		return "Facility [id=" + id + ", facilityName=" + facilityName + "]";
	}
	
	
	
}
