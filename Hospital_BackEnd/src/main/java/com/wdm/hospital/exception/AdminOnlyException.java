package com.wdm.hospital.exception;

public class AdminOnlyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
public AdminOnlyException(String alert) {
	super(alert);
}
}
