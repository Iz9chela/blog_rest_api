package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostDtoV2;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

import static com.springboot.blog.utils.AppConstants.*;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {


    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post
    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save post in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // get all posts rest api
    @Operation(
            summary = "Get All Posts REST API",
            description = "Get All Posts Post REST API is fetch all the posts from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping
    public PostResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                    @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) String sortBy,
                                    @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

//    // get single post by id
//    @GetMapping(value = "/{id}", headers = "X-API-VERSION=1")
//    @Operation(
//            summary = "Get Post by Id REST API",
//            description = "Get Post by Id REST API is used to get single post from database"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Http Status 200 SUCCESS"
//    )
//    public ResponseEntity<PostDto> getPostV1(@PathVariable("id") long id){
//        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
//    }


    // get single post by id
    @GetMapping(value = "/{id}", produces = "application/vnd.pablo.v1+json")
    @Operation(
            summary = "Get Post by Id REST API",
            description = "Get Post by Id REST API is used to get single post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<PostDto> getPostV1(@PathVariable("id") long id){
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }

//    // get single post by id
//    @GetMapping(value = "/v2/{id}", produces = "application/vnd.pablo.v2+json")
//    @Operation(
//            summary = "Get Post by Id REST API",
//            description = "Get Post by Id REST API is used to get single post from database"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Http Status 200 SUCCESS"
//    )
//    public ResponseEntity<PostDtoV2> getPostV2(@PathVariable("id") long id){
//        PostDto postDto = postService.getPost(id);
//
//        PostDtoV2 postDtoV2 = new PostDtoV2();
//        postDtoV2.setId(postDto.getId());
//        postDtoV2.setTitle(postDto.getTitle());
//        postDtoV2.setDescription(postDto.getDescription());
//        postDtoV2.setContent(postDto.getContent());
//        List<String> tags = new ArrayList<>();
//        tags.add("Java");
//        tags.add("Spring boot");
//        tags.add("AWS");
//        postDtoV2.setTags(tags);
//
//        return ResponseEntity.ok(postDtoV2);
//    }

    // update post rest api
    @Operation(
            summary = "Update Post REST API",
            description = "Update Post REST API is used to update a particular post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id") long id){
        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    // delete post rest api
    @Operation(
            summary = "Delete Post REST API",
            description = "Delete Post REST API is used to delete a particular post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){

        postService.deletePostById(id);

        return new ResponseEntity<>("Post was successfully deleted", HttpStatus.OK);
    }

    // get posts by category resty api
    @Operation(
            summary = "Get Post by category REST API",
            description = "Get Post by category REST API is used to get all posts by category from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/category/{category_id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("category_id") Long category_id){
        List<PostDto> posts = postService.getPostsByCategory(category_id);
        return ResponseEntity.ok(posts);
    }

}
