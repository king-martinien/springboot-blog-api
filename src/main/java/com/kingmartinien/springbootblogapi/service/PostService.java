package com.kingmartinien.springbootblogapi.service;


import com.kingmartinien.springbootblogapi.entity.Post;

import java.util.List;

public interface PostService {

    Post createPost(Post post);

    List<Post> getAllPosts();

    Post getPostById(Long id);

    Post updatePost(Long id, Post post);

    void deletePostById(Long id);

}
