package com.example.demo.excepHandlerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exception.EmptyInputExcep;

@RestControllerAdvice
public class ExcepHandlerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> emptyDataHandle(EmptyInputExcep emptyInputExcep){
		
		EmptyInputExcep message = new EmptyInputExcep(null);
		
		message.setErrorMessage("PAGE_NOT_FOUND_LOG_CATEGORY");
		
		ResponseEntity<Object> error = new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		return error;
	}
}
