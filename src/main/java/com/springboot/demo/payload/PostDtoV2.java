package com.springboot.demo.payload;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PostDtoV2 {

private Long id;

   //title should not be null or empty
   //title should have atleast 5 characters
	@NotEmpty
	@Size(min=5, message="Post title should have atleast 5 characters")
	private String title;

	//Post description should not be null or empty
	//Post description should have atleast 10 characters
		@NotEmpty
		@Size(min=10, message="Post description should have atleast 10 characters")
		private String description;
	//Post content should not be empty
		@NotEmpty
	private String content;
	
	private Set<CommentDto> comment;

	private List<String> tags;
	
	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Set<CommentDto> getComment() {
		return comment;
	}

	public void setComment(Set<CommentDto> comment) {
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
