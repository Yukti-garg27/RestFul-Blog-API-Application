package com.springboot.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping
public class PostController {
	@Autowired
	private PostService postService;

	// Create blog post

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/api/v1/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {

		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}

	// To configure HttpResponse, ResponseEntity class is used

	@GetMapping("/api/v1/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir) {
		return new ResponseEntity(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir), HttpStatus.FOUND);
	}

	/*
	 * Versioning
	 * 1.URI: @GetMapping(value="/api/posts/v1/{id}/")
	 * 2.QueryParameter: @GetMapping(value="/api/posts/{id}",params = "version=1")
	 * 3.Custom Headers: @GetMapping(value="/api/posts/{id}",headers="X-API-VERSION=1")
	 * Content Negotiation:
	 *  @GetMapping(value="/api/posts/{id}",produces="application/vsd.demoCompany-v1+json")
	*/
	
	@GetMapping(value="/api/v1/posts/{id}")
	public ResponseEntity<PostDto> getPostByIdV1(@PathVariable Long id) {
		return new ResponseEntity(postService.getPostById(id), HttpStatus.FOUND);
	}

	

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/api/v1/posts/{id}")
	public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto, @PathVariable Long id) {
		return new ResponseEntity(postService.updatePostByID(postDto, id), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/api/v1/posts/{id}")
	public ResponseEntity<String> deletePostById(@PathVariable Long id) {
		return new ResponseEntity(postService.deletePostByID(id), HttpStatus.OK);
	}

}
