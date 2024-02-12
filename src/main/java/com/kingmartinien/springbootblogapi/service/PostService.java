package com.kingmartinien.springbootblogapi.service;


import com.kingmartinien.springbootblogapi.entity.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {

    Post createPost(Post post);

    List<Post> getAllPosts();

    Page<Post> getAllPosts(int page, int size, String sortBy, String sortDir);

    Post getPostById(Long id);

    Post updatePost(Long id, Post post);

    void deletePostById(Long id);

}
