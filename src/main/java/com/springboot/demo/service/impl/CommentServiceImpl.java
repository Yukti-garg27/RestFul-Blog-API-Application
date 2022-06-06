package com.springboot.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.demo.entity.Comment;
import com.springboot.demo.entity.Post;
import com.springboot.demo.exception.BlogAPIException;
import com.springboot.demo.exception.ResourceNotFoundException;
import com.springboot.demo.payload.CommentDto;
import com.springboot.demo.repository.CommentRepository;
import com.springboot.demo.repository.PostRepository;
import com.springboot.demo.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(Long postId, CommentDto commentDto) {

		Comment comment = mapToEntity(commentDto);
		// retrieve post by id

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		comment.setPost(post);
		Comment newComment = commentRepository.save(comment);

		CommentDto newCommentDto = mapToDto(newComment);
		return newCommentDto;
	}

	/*
	 * Applications consisting of similar but different model, use object mapping
	 * make easy To convert from one object model to Another Can use ModelMapper -To
	 * do Mapping - Add dependency also
	 */
	private Comment mapToEntity(CommentDto commentDto) {

		Comment comment = mapper.map(commentDto, Comment.class);
		return comment;
	}

	private CommentDto mapToDto(Comment comment) {

		CommentDto commentDto = mapper.map(comment, CommentDto.class);
		return commentDto;
	}

	@Override
	public List<CommentDto> getAllComments(Long postId) {

		List<Comment> comment = commentRepository.findByPostId(postId);

		return comment.stream().map(listComment -> mapToDto(listComment)).collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {
		Comment comment;

		// Without Throwing Exception
		/*
		 * List<Comment> listOfComment=commentRepository.findByPostId(postId);
		 * 
		 * 
		 * Comment comment= listOfComment.stream().filter(temp->
		 * (temp.getId()==commentId)) .findFirst().get();
		 */
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		// retrieve comment by ID
		comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post or exist");
		}
		return mapToDto(comment);
	}

	@Override
	public CommentDto updateCommentById(Long postId, Long commentId, CommentDto commentDto) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		// retrieve comment by ID
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post or exist");
		}

		comment.setName(commentDto.getName());
		comment.setBody(commentDto.getBody());
		comment.setEmail(commentDto.getEmail());

		Comment updatedComment = commentRepository.save(comment);

		return mapToDto(updatedComment);
	}

	@Override
	public void deleteComment(Long postId, Long commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		// retrieve comment by ID
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post or exist");
		}

		commentRepository.delete(comment);

	}

}
