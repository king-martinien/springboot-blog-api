package com.kingmartinien.springbootblogapi.service;

import com.kingmartinien.springbootblogapi.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Long postId, Comment comment);

    List<Comment> getCommentsByPostId(Long postId);

    Comment getCommentById(Long postId, Long commentId);

    Comment updateComment(Long postId, Long commentId, Comment comment);

    void deleteCommentById(Long postId, Long commentId);

}
