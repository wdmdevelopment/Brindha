package com.example.demo.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Hospital")
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

	@OneToMany(mappedBy = "hospital", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Doctor> doctors;

	@OneToMany(mappedBy = "hospital", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ImgHospital> hospitalImg;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Facility", referencedColumnName = "ID")
	private Facility facilityList;

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

	public Set<ImgHospital> getHospitalImg() {
		return hospitalImg;
	}

	public void setHospitalImg(Set<ImgHospital> hospitalImg) {
		this.hospitalImg = hospitalImg;
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

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public Facility getFacilityList() {
		return facilityList;
	}

	public void setFacilityList(Facility facilityList) {
		this.facilityList = facilityList;
	}

	public Hospital() {

	}

	public Hospital(long hospitalId, String hospitalName, List<User> user, Address address, String contactNum,
			List<Doctor> doctors, Set<ImgHospital> hospitalImg, Facility facilityList) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.user = user;
		this.address = address;
		this.contactNum = contactNum;
		this.doctors = doctors;
		this.hospitalImg = hospitalImg;
		this.facilityList = facilityList;
	}
	
	
	

}
