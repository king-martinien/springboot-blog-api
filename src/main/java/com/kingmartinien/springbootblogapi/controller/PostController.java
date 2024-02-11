package com.kingmartinien.springbootblogapi.controller;

import com.kingmartinien.springbootblogapi.dto.PostDto;
import com.kingmartinien.springbootblogapi.mapper.PostMapper;
import com.kingmartinien.springbootblogapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@RequestBody PostDto postDto) {
        return postMapper.toDto(postService.createPost(postMapper.toEntity(postDto)));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getAllPosts() {
        return postMapper.toDtoList(postService.getAllPosts());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPostById(@PathVariable(name = "id") Long id) {
        return postMapper.toDto(postService.getPostById(id));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto updatePost(@PathVariable(name = "id") Long id, @RequestBody PostDto postDto) {
        return postMapper.toDto(postService.updatePost(id, postMapper.toEntity(postDto)));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePostById(@PathVariable(name = "id") Long id) {
        postService.deletePostById(id);
    }

}
