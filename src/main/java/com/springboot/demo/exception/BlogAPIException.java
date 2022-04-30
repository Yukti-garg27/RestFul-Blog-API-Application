package com.springboot.demo.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException {

	private HttpStatus status;
	private String message;
	public BlogAPIException() {
		// TODO Auto-generated constructor stub
	}
	public BlogAPIException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
