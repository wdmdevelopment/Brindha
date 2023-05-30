package com.wdm.hospital.exception;

public class NoSuchPatientExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public NoSuchPatientExistsException(String errorMsg) {
		super(errorMsg);
	}
}
