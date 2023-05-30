package com.wdm.hospital.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "hospital")
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long hospitalId;

	@Column(name = "Hospital_Name")
	private String hospitalName;

	@OneToMany(mappedBy = "hospital", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<User> user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Address")
	private Address address;

	@Column(name = "PhNo")
	private String contactNum;

//	@OneToMany(mappedBy = "hospital", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<Doctor> doctors;

	@OneToMany(mappedBy = "hospital", 
			orphanRemoval = true, cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Slot> slot; 

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "HoispitalImage", referencedColumnName = "imageId")
	private ImgHospital hospitalImage;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "Facility", referencedColumnName = "ID")
//	private Facility facility;
	
	@ManyToMany
	@JoinTable(name = "hospital_service", joinColumns = @JoinColumn(name = "hospital_id"), inverseJoinColumns = @JoinColumn(name = "facility_id"))
	private List<Facility> facility;
	
	
	private LocalDateTime addedAt;
	
	

	public long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public List<Slot> getSlot() {
		return slot;
	}

	public void setSlot(List<Slot> slot) {
		this.slot = slot;
	}

	public ImgHospital getHospitalImage() {
		return hospitalImage;
	}

	public void setHospitalImage(ImgHospital hospitalImage) {
		this.hospitalImage = hospitalImage;
	}

	public Hospital(long hospitalId, String hospitalName, List<User> user, Address address, String contactNum,
			List<Slot> slot, ImgHospital hospitalImage, List<Facility> facility, LocalDateTime addedAt) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.user = user;
		this.address = address;
		this.contactNum = contactNum;
		this.slot = slot;
		this.hospitalImage = hospitalImage;
		this.facility = facility;
		this.addedAt = addedAt;
	}

	public List<Facility> getFacility() {
		return facility;
	}

	public void setFacility(List<Facility> facility) {
		this.facility = facility;
	}

	public Hospital() {

	}

	public LocalDateTime getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(LocalDateTime addedAt) {
		this.addedAt = addedAt;
	}
}
