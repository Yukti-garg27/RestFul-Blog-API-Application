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
import com.springboot.demo.payload.PostDtoV2;
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
	@PostMapping("/api/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {

		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}

	// To configure HttpResponse, ResponseEntity class is used

	@GetMapping("/api/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir) {
		return new ResponseEntity(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir), HttpStatus.FOUND);
	}

	@GetMapping("/api/posts/v1/{id}")
	public ResponseEntity<PostDto> getPostByIdV1(@PathVariable Long id) {
		return new ResponseEntity(postService.getPostById(id), HttpStatus.FOUND);
	}

	@GetMapping("/api/posts/v2/{id}")
	public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable Long id) {
		PostDto postDto = postService.getPostById(id);
		PostDtoV2 postDtoV2 = new PostDtoV2();
		postDtoV2.setId(postDto.getId());
		postDtoV2.setTitle(postDto.getTitle());
		postDtoV2.setContent(postDto.getContent());
		postDtoV2.setDescription(postDto.getDescription());
		postDtoV2.setComment(postDto.getComment());
		List<String> tags = new ArrayList<>();
		tags.add("Java");
		tags.add("Spring Boot");
		postDtoV2.setTags(tags);
		return new ResponseEntity(postDtoV2, HttpStatus.FOUND);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/api/posts/{id}")
	public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto, @PathVariable Long id) {
		return new ResponseEntity(postService.updatePostByID(postDto, id), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/api/posts/{id}")
	public ResponseEntity<String> deletePostById(@PathVariable Long id) {
		return new ResponseEntity(postService.deletePostByID(id), HttpStatus.OK);
	}

}
