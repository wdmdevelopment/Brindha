package com.wdm.hospital.exception;

import java.util.List;

import java.util.stream.Collectors;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class CustomExceptionHandle {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ErrorMessage> IdNotFoundException(IdNotFoundException ex, WebRequest webRequest) {
		ErrorMessage msg = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(msg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<ErrorMessage> DoctorNotFoundException(DoctorNotFoundException ex) {
		ErrorMessage msg = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(msg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchPatientExistsException.class)
	public ResponseEntity<ErrorMessage> NoSuchPatientExistsException(NoSuchPatientExistsException ex) {
		ErrorMessage msg = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(msg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class, HttpMessageNotReadableException.class })
	public ResponseEntity<ExceptionValidation> handleBindingErrors(MethodArgumentNotValidException ex) {
		List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
				.map(FieldError -> FieldError.getDefaultMessage()).collect(Collectors.toList());
		ExceptionValidation notFound = new ExceptionValidation(errorList.toString(), HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(notFound, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DoctorOnlyException.class)
	public ResponseEntity<ErrorMessage> DoctorOnlyException(DoctorOnlyException ex) {
		ErrorMessage msg = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(msg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AdminOnlyException.class)
	public ResponseEntity<ErrorMessage> AdminOnlyException(AdminOnlyException ex) {
		ErrorMessage msg = new ErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(msg, HttpStatus.NOT_FOUND);
	}

}
