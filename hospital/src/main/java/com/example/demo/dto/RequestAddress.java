package com.example.demo.dto;

public class RequestAddress {

		private String city;

		private String district;

		private String state;

		private String country;

		private String pincode;

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getPincode() {
			return pincode;
		}

		public void setPincode(String pincode) {
			this.pincode = pincode;
		}

		public String toString() {
			return "RequestAddress [district=" + district + ", city=" + city + ", state=" + state + ", country=" + country
					+ ", pincode=" + pincode + "]";
		}
	}
