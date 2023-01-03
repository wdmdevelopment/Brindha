package com.example.demo.Entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity

@Table(name = "HOSPITAL")

public class Hospital {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Hospital_ID")
	private long hospitalId;

	@Column(name = "Hospital_Name")
	private String hospitalName;

	@Column(name = "Doctor_Name")
	private String doctorName;
	
	@Column(name = "Doctor_Speciality")
	private String specialisation;
	
	@Column(name = "Hospital_Address")
	private String address;
	
	@Column(name = "Admission_Date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.DATE)
	private Date admittedAt=new Date(System.currentTimeMillis());
	
	@Column(name = "Available")
	private char availablity;

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

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	 @JsonIgnore    
	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getAdmittedAt() {
		return admittedAt;
	}

	public void setAdmittedAt(Date admittedAt) {
		this.admittedAt = admittedAt;
	}
	public char getAvailablity() {
		return availablity;
	}

	public void setAvailablity(char availablity) {
		this.availablity = availablity;
	}

	public Hospital() {
		
	}

	public Hospital(long hospitalId, String hospitalName, String doctorName, String specialisation, String address,
			Date admittedAt, char availablity) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.doctorName = doctorName;
		this.specialisation = specialisation;
		this.address = address;
		this.admittedAt = admittedAt;
		this.availablity = availablity;
	}

	@Override
	public String toString() {
		return "Hospital [hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", doctorName=" + doctorName
				+ ", specialisation=" + specialisation + ", address=" + address + ", admittedAt=" + admittedAt
				+ ", availablity=" + availablity + "]";
	}

	

}
