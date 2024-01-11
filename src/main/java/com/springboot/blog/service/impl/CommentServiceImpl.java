package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        //retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        // set post to comment entity
        comment.setPost(post);

        //save entity to db
        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);

    }

    @Override
    public List<CommentDto> getCommentsByPostId(long post_Id) {

        List<Comment> comments = commentRepository.findByPostId(post_Id);

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getComment(long post_Id, long comment_id) {
        //retrieve post entity by id
        Post post = postRepository.findById(post_Id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", post_Id));

        Comment comment = commentRepository.findById(comment_id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", comment_id));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to the post!");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(long post_id, long comment_id, CommentDto commentDto) {
        Post post = postRepository.findById(post_id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", post_id));

        Comment comment = commentRepository.findById(comment_id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", comment_id));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to the post");
        }

        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());

        Comment updated_comment = commentRepository.save(comment);

        return mapToDto(updated_comment);
    }

    @Override
    public void deleteComment(long post_id, long comment_id) {

        Post post = postRepository.findById(post_id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", post_id));

        Comment comment = commentRepository.findById(comment_id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", comment_id));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        commentRepository.delete(comment);

    }

    //convert entity to DTO
    private CommentDto mapToDto(Comment comment){

        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());

        return commentDto;
    }

    // convert DTO to entity
    private Comment mapToEntity(CommentDto commentDto){

        Comment comment = modelMapper.map(commentDto, Comment.class);
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());

        return comment;
    }
}
