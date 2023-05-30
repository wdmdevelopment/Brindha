package com.wdm.hospital.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Facility")
public class Facility {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "FacilityName")
	private String facilityName;

//	@ManyToOne
//	@JoinColumn(name = "hospital_id")
//	private Hospital hospital;

	
	@ManyToMany(mappedBy = "facility")
	@JsonIgnore
    private List<Hospital> hospitals;

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

	public List<Hospital> getHospitals() {
		return hospitals;
	}

	public void setHospitals(List<Hospital> hospitals) {
		this.hospitals = hospitals;
	}

	public Facility(long id, String facilityName, List<Hospital> hospitals) {
		super();
		this.id = id;
		this.facilityName = facilityName;
		this.hospitals = hospitals;
	}

	
	
	
	
	
	 
 
}
