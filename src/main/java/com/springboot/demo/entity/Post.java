package com.springboot.demo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.springboot.demo.payload.CommentDto;

@Entity
@Table(
		name="posts",
		uniqueConstraints={@UniqueConstraint(columnNames = {"title"})})
public class Post {

	@Id
	@GeneratedValue(
			strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false)
	private String content;
	//To maintain owning relationship
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<Comment> comments=new HashSet<>(); 
	
	public Post() {}

	public Post(Long id, String title, String description, String content, Set<Comment> comments) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
		this.comments = comments;
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

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> set) {
		this.comments = set;
	}
	
	
}
