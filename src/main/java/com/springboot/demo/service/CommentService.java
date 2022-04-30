package com.springboot.demo.service;

import java.util.List;

import com.springboot.demo.payload.CommentDto;

public interface CommentService {

	CommentDto createComment(Long postId, CommentDto commentDto);
	List<CommentDto> getAllComments(Long postId);
	
	CommentDto getCommentById(Long postId, Long commentId);
}
