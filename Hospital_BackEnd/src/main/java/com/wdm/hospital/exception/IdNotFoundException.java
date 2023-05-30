package com.wdm.hospital.exception;

public class IdNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public IdNotFoundException(String error) {
		super(error);

	}
}
