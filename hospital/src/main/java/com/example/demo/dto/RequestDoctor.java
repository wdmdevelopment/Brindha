package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.entity.Address;
import com.example.demo.entity.Hospital;
import com.example.demo.entity.Patient;

public class RequestDoctor {


		private String doctorName;

		private String doctorSpecialist;

		private Hospital hospital;

		private Set<Patient> patientList = new HashSet<>();

		private Address doctorAddress;

		public String getDoctorName() {
			return doctorName;
		}

		public void setDoctorName(String doctorName) {
			this.doctorName = doctorName;
		}

		public Hospital getHospital() {
			return hospital;
		}

		public void setHospital(Hospital hospital) {
			this.hospital = hospital;
		}

		public Set<Patient> getpatientList() {
			return patientList;
		}

		public void setpatientList(Set<Patient> patientList) {
			this.patientList = patientList;
		}

		public String getDoctorSpecialist() {
			return doctorSpecialist;
		}

		public void setDoctorSpecialist(String doctorSpecialist) {
			this.doctorSpecialist = doctorSpecialist;
		}

		public Address getDoctorAddress() {
			return doctorAddress;
		}

		public void setDoctorAddress(Address doctorAddress) {
			this.doctorAddress = doctorAddress;
		}

		public RequestDoctor() {

		}

		public RequestDoctor(String doctorName, String doctorSpecialist, Hospital hospital, Set<Patient> patientList,
				Address doctorAddress) {
			super();

			this.doctorName = doctorName;
			this.doctorSpecialist = doctorSpecialist;
			this.hospital = hospital;
			this.patientList = patientList;
			this.doctorAddress = doctorAddress;
		}

		public String toString() {
			return "RequestDoctor [doctorName=" + doctorName + ", doctorSpecialist=" + doctorSpecialist + ", hospital="
					+ hospital + ", patientList=" + patientList + ", doctorAddress=" + doctorAddress + "]";
		}
	}
