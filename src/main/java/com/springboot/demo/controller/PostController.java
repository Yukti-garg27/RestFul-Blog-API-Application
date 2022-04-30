package com.springboot.demo.controller;

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

@RestController
@RequestMapping("/api/posts")
public class PostController {
	@Autowired
	private PostService postService;
	
	//Create blog post

	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
		
		
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
	}	
	
	//To configure HttpResponse, ResponseEntity class is used
    
	@GetMapping
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(name="pageNo", defaultValue="0", required =false) int pageNo,
			@RequestParam(name="pageSize", defaultValue="10", required =false) int pageSize){
		return new ResponseEntity(
				postService.getAllPosts(pageNo,pageSize),HttpStatus.FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
		return new ResponseEntity(postService.getPostById(id),HttpStatus.FOUND);
	}
	
	

	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto,@PathVariable Long id){
		return new ResponseEntity(postService.updatePostByID(postDto,id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePostById(@PathVariable Long id){
		return new ResponseEntity(postService.deletePostByID(id),HttpStatus.OK);
	}
	
}