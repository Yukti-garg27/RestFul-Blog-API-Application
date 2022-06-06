package com.springboot.demo.payload;

import java.util.List;
import java.util.Set;

import com.springboot.demo.entity.Role;

public class SignUpDto {

	private String name;
	private String username;
	private String email;
	private String password;
	private List<String> roles;
	public SignUpDto() {
		// TODO Auto-generated constructor stub
	}
	
	public SignUpDto(String name, String username, String email, String password, List<String> roles) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
