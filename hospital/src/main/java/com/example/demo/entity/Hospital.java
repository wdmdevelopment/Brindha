package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Hospital")
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long hospitalId;

	@Column(name = "Hospital_Name")
	private String hospitalName;

	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	private List<User> user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Address")
	private Address address;

	@Column(name = "PhNo")
	private String contactNum;

	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	private List<Doctor> doctors;

	@OneToOne
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
			List<Doctor> doctors, Facility facilityList) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.user = user;
		this.address = address;
		this.contactNum = contactNum;
		this.doctors = doctors;
		this.facilityList = facilityList;
	}

	@Override
	public String toString() {
		return "Hospital [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", user=" + user + ", address="
				+ address + ", contactNum=" + contactNum + ", doctors=" + doctors + ", facilityList=" + facilityList
				+ "]";
	}
	

}
