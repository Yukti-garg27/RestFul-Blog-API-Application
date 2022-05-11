package com.springboot.demo.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CommentDto {

	
	private Long id;
	@NotEmpty
	@Size(min=5, message="Name should have atleast 5 characters")
	private String name;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	@Size(min=10, message="Comment body should have atleast 10 characters")
	private String body;
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getBody() {
		return body;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
	
}
