package com.springboot.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.demo.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
