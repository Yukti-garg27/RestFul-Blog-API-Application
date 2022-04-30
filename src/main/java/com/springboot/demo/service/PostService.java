package com.springboot.demo.service;

import java.util.List;

import com.springboot.demo.payload.PostDto;
import com.springboot.demo.payload.PostResponse;

public interface PostService {

	
	PostDto createPost(PostDto postDto);
	
	PostResponse getAllPosts(int pageNo,int pageSize);
	
	PostDto getPostById(Long id);
	
	PostDto updatePostByID(PostDto postDto, Long id);
	String deletePostByID(Long id);
}
