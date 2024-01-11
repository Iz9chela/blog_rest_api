package com.springboot.blog.payload;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        description = "Comment Model Information"
)
public class CommentDto {

    @Schema(
            description = "Comment Id"
    )
    private long id;

    @Schema(
            description = "Comment name"
    )
    @NotBlank(message = "Name should not be null or blank")
    private String name;

    @Schema(
            description = "Comment email"
    )
    @NotBlank(message = "Email should not be null or blank")
    @Email
    private String email;

    @Schema(
            description = "Comment body"
    )
    @NotBlank
    @Size(min = 10, message = "Body must be minimum 10 characters")
    private String body;


}

