package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Schema(
        description = "PostDto Model Information"
)
public class PostDtoV2 {

    @Schema(
            description = "Post Id"
    )
    private long id;

    @Schema(
            description = "Blog Post Title"
    )
    @NotBlank
    @Size(min = 2, message = "Post title should have at list 2 characters")
    private String title;

    @Schema(
            description = "Blog Post Description"
    )
    @NotBlank
    @Size(min = 10, message = "Post description should have at list 10 characters")
    private String description;

    @Schema(
            description = "Blog Post Content"
    )
    @NotBlank
    private String content;

    @Schema(
            description = "Comments related to the current post"
    )
    private Set<CommentDto> comments;

    @Schema(
            description = "Blog Post Category"
    )
    @NotBlank
    private Long categoryId;

    private List<String> tags;

}

