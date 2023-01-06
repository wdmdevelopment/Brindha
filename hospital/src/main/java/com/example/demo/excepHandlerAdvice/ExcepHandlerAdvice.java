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
		
		message.setErrorMessage("Data_Not_Found");
		
		ResponseEntity<Object> error = new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		return error;
	}
	
//	@ExceptionHandler
//	public ResponseEntity<Object> handleMethodArgumentNotValid( 
//			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
//		ErrorResponse error = new ErrorResponse9(new Date(), ex.getMessage(), ex.getBindingResult().toString());
//		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//	}
}
