package com.example.demo.exception;

public class ExceptionValidation {
	public String status;
	public String message;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ExceptionValidation(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	

}
