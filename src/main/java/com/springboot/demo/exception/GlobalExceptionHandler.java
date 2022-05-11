package com.springboot.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.springboot.demo.payload.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {


	//Handle specific exceptions
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, 
			                          WebRequest request){
		
		ErrorDetails details= new ErrorDetails(new Date(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(BlogAPIException.class)
	public ResponseEntity<ErrorDetails> handleBlogAPIException(ResourceNotFoundException exception, 
			                          WebRequest request){
		
		ErrorDetails details= new ErrorDetails(new Date(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
		
	}
	
}
