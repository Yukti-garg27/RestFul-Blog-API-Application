package com.springboot.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.payload.PostDto;
import com.springboot.demo.payload.PostResponse;
import com.springboot.demo.service.PostService;
import com.springboot.demo.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	@Autowired
	private PostService postService;
	
	//Create blog post

	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
		
		
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
	}	
	
	//To configure HttpResponse, ResponseEntity class is used
    
	@GetMapping
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(name="pageNo", defaultValue=AppConstants.DEFAULT_PAGE_NUMBER, required =false) int pageNo,
			@RequestParam(name="pageSize", defaultValue=AppConstants.DEFAULT_PAGE_SIZE, required =false) int pageSize,
			@RequestParam(name="sortBy", defaultValue=AppConstants.DEFAULT_SORT_BY, required =false) String sortBy,	
			@RequestParam(name="sortDir", defaultValue=AppConstants.DEFAULT_SORT_DIR, required =false) String sortDir){
			return new ResponseEntity(
				postService.getAllPosts(pageNo,pageSize,sortBy,sortDir),HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
		return new ResponseEntity(postService.getPostById(id),HttpStatus.FOUND);
	}
	
	

	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto,@PathVariable Long id){
		return new ResponseEntity(postService.updatePostByID(postDto,id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePostById(@PathVariable Long id){
		return new ResponseEntity(postService.deletePostByID(id),HttpStatus.OK);
	}
	
}
