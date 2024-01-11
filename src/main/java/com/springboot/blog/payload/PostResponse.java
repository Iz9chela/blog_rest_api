package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "List of all posts and related comments and categories"
)
public class PostResponse {

    @Schema(
            description = "List of all posts"
    )
    private List<PostDto> content;
    @Schema(
            description = "Current page"
    )
    private int pageNo;
    @Schema(
            description = "Current page max number of elements"
    )
    private int pageSize;
    @Schema(
            description = "All count of elements"
    )
    private long totalElements;
    @Schema(
            description = "Count of all pages"
    )
    private int totalPages;
    @Schema(
            description = "Shows if it is the last page"
    )
    private boolean last;

}
