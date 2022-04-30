package com.springboot.demo.service;

import java.util.List;

import com.springboot.demo.payload.PostDto;

public interface PostService {

	
	PostDto createPost(PostDto postDto);
	
	List<PostDto> getAllPosts();
	
	PostDto getPostById(Long id);
	
	PostDto updatePostByID(PostDto postDto, Long id);
	String deletePostByID(Long id);
}
