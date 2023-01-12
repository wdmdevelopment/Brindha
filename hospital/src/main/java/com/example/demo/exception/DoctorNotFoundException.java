package com.example.demo.exception;

public class DoctorNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DoctorNotFoundException(String message) {
		super(message);

	}
}