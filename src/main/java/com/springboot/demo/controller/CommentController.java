package com.springboot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.payload.CommentDto;
import com.springboot.demo.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, 
			@RequestBody CommentDto commentDto){
	
		return new ResponseEntity<>(commentService.createComment(postId, commentDto),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> getAllComment(@PathVariable Long postId){
	
		return new ResponseEntity(commentService.getAllComments(postId),HttpStatus.OK);
		
	}
	
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentByID(@PathVariable Long postId,
			@PathVariable Long commentId){
	
		return new ResponseEntity(commentService.getCommentById(postId,commentId),HttpStatus.OK);
		
	}
	
}
