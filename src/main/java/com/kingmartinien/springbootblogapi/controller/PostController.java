package com.kingmartinien.springbootblogapi.controller;

import com.kingmartinien.springbootblogapi.dto.PostDto;
import com.kingmartinien.springbootblogapi.mapper.PostMapper;
import com.kingmartinien.springbootblogapi.service.PostService;
import com.kingmartinien.springbootblogapi.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@Valid @RequestBody PostDto postDto) {
        return postMapper.toDto(postService.createPost(postMapper.toEntity(postDto)));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getAllPosts() {
        return postMapper.toDtoList(postService.getAllPosts());
    }

    @GetMapping("page")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostDto> getAllPosts(
            @RequestParam(name = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(name = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int size,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
        return postService.getAllPosts(page, size, sortBy, sortDir).map(postMapper::toDto);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto getPostById(@PathVariable(name = "id") Long id) {
        return postMapper.toDto(postService.getPostById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto updatePost(@PathVariable(name = "id") Long id, @Valid @RequestBody PostDto postDto) {
        return postMapper.toDto(postService.updatePost(id, postMapper.toEntity(postDto)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePostById(@PathVariable(name = "id") Long id) {
        postService.deletePostById(id);
    }

}
