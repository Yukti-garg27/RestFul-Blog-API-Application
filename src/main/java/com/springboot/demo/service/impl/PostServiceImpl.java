package com.springboot.demo.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.demo.entity.Post;
import com.springboot.demo.exception.ResourceNotFoundException;
import com.springboot.demo.payload.PostDto;
import com.springboot.demo.payload.PostResponse;
import com.springboot.demo.repository.PostRepository;
import com.springboot.demo.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public PostDto createPost(PostDto postDto) {
		Post post=new Post();
		//convert Dto to entity
		post= mapToEntity(postDto);
		Post newPost= postRepository.save(post);
		PostDto postResponse =new PostDto();
		//convert entity to Dto
	    postResponse=mapToDto(newPost);
		return postResponse;
	}

	//convert dto to entity
	
	@Override
	public String deletePostByID(Long id) {
		// TODO Auto-generated method stub
		
		 Post post=postRepository.findById(id).orElseThrow(
       		  ()->new ResourceNotFoundException("Post","id", id)
                   );
		 
		 postRepository.delete(post);
		 return "deleted";
		
	}

	public Post mapToEntity(PostDto postDto) {
		Post post=new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		return post;
	}
	
	//convert entity to dto
	
	public PostDto mapToDto(Post post) {
		PostDto postResponse =new PostDto();
		postResponse.setId(post.getId());
			postResponse.setTitle(post.getTitle());
			postResponse.setDescription(post.getDescription());
			postResponse.setContent(post.getContent());
			return postResponse;
	}
	
	@Override
	public PostResponse getAllPosts(int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		
		//create Pageable Instance
		
		Pageable pageable=PageRequest.of(pageNo, pageSize);
		
		
		Page<Post> allPosts = postRepository.findAll(pageable);
		
		//Get content from page object
		List<Post> listOfPosts= allPosts.getContent();
		
		
		List<PostDto> content= listOfPosts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
	
	  PostResponse postResponse=new PostResponse();
	  postResponse.setContent(content);
	  postResponse.setPageNo(allPosts.getNumber());
	  postResponse.setPageSize(allPosts.getSize());
	  postResponse.setTotalElements(allPosts.getTotalElements());
	  postResponse.setTotalPages(allPosts.getTotalPages());
	  postResponse.setLast(allPosts.isLast());
	  
	  return postResponse;
	  
	}

	@Override
	public PostDto getPostById(Long id) {
          Post post=postRepository.findById(id).orElseThrow(
        		  ()->new ResourceNotFoundException("Post","id", id)
                    );
		return mapToDto(post);
	}

	@Override
	public PostDto updatePostByID(PostDto postDto, Long id) {
		// TODO Auto-generated method stub
		
		Post post=postRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Post","id", id)
                );
		
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post updatedPost=postRepository.save(post);
		
		
		
		return mapToDto(updatedPost);
	}

}
