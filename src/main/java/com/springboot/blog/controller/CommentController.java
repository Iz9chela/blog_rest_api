package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(
        name = "CRUD REST APIs for Comment Resource"
)
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // create comment for blog
    @Operation(
            summary = "Create Comment REST API",
            description = "Create Comment REST API is used to save comment in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{post_id}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "post_id") long post_id,
                                                    @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(post_id,commentDto), HttpStatus.CREATED);
    }

    // get all comments rest api
    @Operation(
            summary = "Get All Comments REST API",
            description = "Get All Comments Post REST API is fetch all the comments from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/{post_id}/comments")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable(value = "post_id") long post_id){
        return new ResponseEntity<>(commentService.getCommentsByPostId(post_id), HttpStatus.OK);
    }

    // get single comments by postId
    @Operation(
            summary = "Get Comment by PostId REST API",
            description = "Get Comment by PostId REST API is used to get single comment from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/{post_id}/comments/{comment_id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable(value = "post_id") long post_id,
                                                 @PathVariable(value = "comment_id") long comment_id){
        return new ResponseEntity<>(commentService.getComment(post_id, comment_id), HttpStatus.OK);
    }

    // update single comment by postId
    @Operation(
            summary = "Update Comment by PostId REST API",
            description = "Update Comment by PostId REST API is used to update a particular comment in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{post_id}/comments/{comment_id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "post_id") long post_id,
                                                 @PathVariable(value = "comment_id") long comment_id,
                                                 @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.updateComment(post_id, comment_id,commentDto), HttpStatus.OK);
    }

    // delete single comment by postId
    @Operation(
            summary = "Delete Comment by PostId REST API",
            description = "Delete Comment by PostId REST API is used to delete a particular comment in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{post_id}/comments/{comment_id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "post_id") long post_id,
                                                    @PathVariable(value = "comment_id") long comment_id){
        commentService.deleteComment(post_id, comment_id);
        return new ResponseEntity<>("Comment was successfully deleted", HttpStatus.OK);
    }
}
