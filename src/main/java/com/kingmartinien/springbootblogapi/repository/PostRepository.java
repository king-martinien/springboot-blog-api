package com.kingmartinien.springbootblogapi.repository;

import com.kingmartinien.springbootblogapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
