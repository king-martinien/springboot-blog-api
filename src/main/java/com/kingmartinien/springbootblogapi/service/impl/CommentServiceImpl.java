package com.kingmartinien.springbootblogapi.service.impl;

import com.kingmartinien.springbootblogapi.entity.Comment;
import com.kingmartinien.springbootblogapi.entity.Post;
import com.kingmartinien.springbootblogapi.exception.BlogApiException;
import com.kingmartinien.springbootblogapi.exception.ResourceNotFoundException;
import com.kingmartinien.springbootblogapi.repository.CommentRepository;
import com.kingmartinien.springbootblogapi.service.CommentService;
import com.kingmartinien.springbootblogapi.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentServiceImpl(CommentRepository commentRepository, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    @Override
    public Comment createComment(Long postId, Comment comment) {
        Post post = postService.getPostById(postId);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public Comment getCommentById(Long postId, Long commentId) {
        Post post = postService.getPostById(postId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId.toString()));
        if (comment.getPost().getId().equals(post.getId())) {
            return comment;
        } else {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to the post");
        }
    }

    @Override
    public Comment updateComment(Long postId, Long commentId, Comment comment) {
        Comment commentToUpdate = getCommentById(postId, commentId);
        commentToUpdate.setName(comment.getName());
        commentToUpdate.setEmail(comment.getEmail());
        commentToUpdate.setMessage(comment.getMessage());
        return commentRepository.save(commentToUpdate);
    }

    @Override
    public void deleteCommentById(Long postId, Long commentId) {
        Comment comment = getCommentById(postId, commentId);
        commentRepository.delete(comment);
    }

}
