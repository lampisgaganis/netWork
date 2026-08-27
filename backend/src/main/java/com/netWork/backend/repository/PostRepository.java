package com.netWork.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netWork.backend.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
