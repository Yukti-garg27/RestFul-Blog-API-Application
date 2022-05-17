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
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.payload.CommentDto;
import com.springboot.demo.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "CRUD REST APIs for Comment Respource")
@RestController
@RequestMapping("/api/v1")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@ApiOperation(value = "Create Comment REST API")
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable Long postId,
			@Valid @RequestBody CommentDto commentDto) {

		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);

	}

	@ApiOperation(value = "Get All Comments By Post ID REST API")
	@GetMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> getAllComment(@PathVariable Long postId) {

		return new ResponseEntity(commentService.getAllComments(postId), HttpStatus.OK);

	}

	@ApiOperation(value = "Get Single Comment By ID REST API")
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentByID(@PathVariable Long postId, @PathVariable Long commentId) {

		return new ResponseEntity(commentService.getCommentById(postId, commentId), HttpStatus.OK);

	}

	@ApiOperation(value = "Update Comment By ID REST API")
	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateCommentByID(@PathVariable Long postId, @PathVariable Long commentId,
			@Valid @RequestBody CommentDto commentDto) {

		return new ResponseEntity(commentService.updateCommentById(postId, commentId, commentDto), HttpStatus.OK);

	}

	@ApiOperation(value = "Delete Comment By ID REST API")
	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
		commentService.deleteComment(postId, commentId);
		return new ResponseEntity("Comment deleted successfully", HttpStatus.OK);

	}

}
