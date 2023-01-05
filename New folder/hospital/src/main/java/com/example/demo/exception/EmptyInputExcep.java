package com.example.demo.exception;

public class EmptyInputExcep  {

	
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public EmptyInputExcep(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	

	
}
