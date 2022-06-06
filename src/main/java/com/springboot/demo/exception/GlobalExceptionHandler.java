package com.springboot.demo.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.demo.payload.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{


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
	
	//Global Exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, 
			                          WebRequest request){
		
		ErrorDetails details= new ErrorDetails(new Date(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	/*
	 * Cutomizing Validation Response
	 * Two ways:
	* 1. Override the method handleMethodArgumentNotValid of class ResponseEntityExceptionHandler
	* Create a map and add all the errors message to it with status code BAD_REQUEST
	
	*  2. Handle MethodArgumentNotValidException similar to ResourceNotFoundException/BlogAPIException
	*  
	*/
	
	/*
	 * --1st Method
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			    HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		
		Map<String,String> errors=new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach(error->{
			String fieldName =((FieldError)error).getField();
			String fieldMessage = error.getDefaultMessage();
			errors.put(fieldName, fieldMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		
	}
	
	/*
	 * --2nd Method
	 * 
	 */
	/*
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, 
			                          WebRequest request){
		
Map<String,String> errors=new HashMap<>();
		
		exception.getBindingResult().getAllErrors().forEach(error->{
			String fieldName =((FieldError)error).getField();
			String fieldMessage = error.getDefaultMessage();
			errors.put(fieldName, fieldMessage);
		});
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		
	}
	*/
}
