package com.kingmartinien.springbootblogapi.controller;

import com.kingmartinien.springbootblogapi.dto.CommentDto;
import com.kingmartinien.springbootblogapi.mapper.CommentMapper;
import com.kingmartinien.springbootblogapi.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts/{postId}/comments")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@PathVariable(name = "postId") Long postId,
                                    @Valid @RequestBody CommentDto commentDto) {
        return commentMapper.toDto(this.commentService.createComment(postId, commentMapper.toEntity(commentDto)));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getCommentsByPostId(@PathVariable(name = "postId") Long postId) {
        return commentMapper.toDtoList(commentService.getCommentsByPostId(postId));
    }

    @GetMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getCommentById(@PathVariable(name = "postId") Long postId,
                                     @PathVariable(name = "commentId") Long commentId) {
        return commentMapper.toDto(commentService.getCommentById(postId, commentId));
    }

    @PutMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto updateComment(@PathVariable(name = "postId") Long postId,
                                    @PathVariable(name = "commentId") Long commentId,
                                    @Valid @RequestBody CommentDto commentDto) {
        return commentMapper.toDto(commentService.updateComment(postId, commentId, commentMapper.toEntity(commentDto)));
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentById(@PathVariable(name = "postId") Long postId,
                                  @PathVariable(name = "commentId") Long commentId) {
        commentService.deleteCommentById(postId, commentId);
    }

}
