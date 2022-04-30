package com.springboot.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.entity.Post;
import com.springboot.demo.exception.ResourceNotFoundException;
import com.springboot.demo.payload.PostDto;
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
	public List<PostDto> getAllPosts() {
		// TODO Auto-generated method stub
		
		List<Post> allPosts = postRepository.findAll();
		
		return allPosts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
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
