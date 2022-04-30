package com.springboot.demo.payload;

public class CommentDto {

	
	private Long id;
	private String name;
	private String email;
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
