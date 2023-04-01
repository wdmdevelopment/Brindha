package com.example.demo.entity;

import java.sql.Date;
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
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long staffId;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Hospital> hospital;

	@Column(name = "StaffName")
	private String staffName;

	@Column(name = "Roll")
	private String designation;

	@Column(name = "DOB")
	private Date dob;

	@Column(name = "DOB")
	private int age;

	@OneToOne
	@JoinColumn(name = "Address", referencedColumnName = "ID")
	private Address address;

	public long getStaffId() {
		return staffId;
	}

	public void setStaffId(long staffId) {
		this.staffId = staffId;
	}

	public List<Hospital> getHospital() {
		return hospital;
	}

	public void setHospital(List<Hospital> hospital) {
		this.hospital = hospital;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Employee() {
	}

	public Employee(long staffId, List<Hospital> hospital, String staffName, String designation, Date dob, int age,
			Address address) {
		super();
		this.staffId = staffId;
		this.hospital = hospital;
		this.staffName = staffName;
		this.designation = designation;
		this.dob = dob;
		this.age = age;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [staffId=" + staffId + ", hospital=" + hospital + ", staffName=" + staffName + ", designation="
				+ designation + ", dob=" + dob + ", age=" + age + ", address=" + address + "]";
	}

}
