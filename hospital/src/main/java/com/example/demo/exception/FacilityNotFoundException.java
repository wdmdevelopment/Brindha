package com.example.demo.exception;

public class FacilityNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMsg;
	
	
	public String getErrorMsg() {
		return errorMsg;
	}


	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	public FacilityNotFoundException(String errorMsg) {
		super(errorMsg);
	}

}
