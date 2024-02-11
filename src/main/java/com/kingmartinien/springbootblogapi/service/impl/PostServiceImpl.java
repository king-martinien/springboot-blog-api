package com.kingmartinien.springbootblogapi.service.impl;

import com.kingmartinien.springbootblogapi.entity.Post;
import com.kingmartinien.springbootblogapi.exception.ResourceNotFoundException;
import com.kingmartinien.springbootblogapi.repository.PostRepository;
import com.kingmartinien.springbootblogapi.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
    }

    @Override
    public Post updatePost(Long id, Post post) {
        Post postToUpdate = getPostById(id);
        postToUpdate.setTitle(post.getTitle());
        postToUpdate.setDescription(post.getDescription());
        postToUpdate.setContent(post.getContent());
        return postRepository.save(postToUpdate);
    }

    @Override
    public void deletePostById(Long id) {
        Post postToDelete = getPostById(id);
        postRepository.deleteById(postToDelete.getId());
    }

}
