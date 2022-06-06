package com.springboot.demo.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description  = "Comment model information")
public class CommentDto {

	@ApiModelProperty(value = "Comment id")
	private long id;
	// name should not be null or empty
	@ApiModelProperty(value = "Comment name")
	@NotEmpty(message = "Name should not be null or empty")
	private String name;

	// email should not be null or empty
	// email field validation
	@ApiModelProperty(value = "Comment email")
	@NotEmpty(message = "Email should not be null or empty")
	@Email
	private String email;

	// comment body should not be bull or empty
	// Comment body must be minimum 10 characters
	@NotEmpty
	@ApiModelProperty(value = "Comment body")
	@Size(min = 10, message = "Comment body must be minimum 10 characters")
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
