package com.springboot.blog.service;

import com.springboot.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long post_Id);

    CommentDto getComment(long post_Id, long comment_Id);

    CommentDto updateComment(long post_Id, long comment_Id, CommentDto commentDto);

    void deleteComment(long post_Id, long comment_Id);

}
