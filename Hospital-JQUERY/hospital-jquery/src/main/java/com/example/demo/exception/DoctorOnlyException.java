package com.example.demo.exception;

public class DoctorOnlyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DoctorOnlyException(String message) {
		super(message);
}
}