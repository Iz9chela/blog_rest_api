package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "Category Model Information"
)
public class CategoryDto {

    @Schema(
            description = "Category Id"
    )
    private long id;
    @Schema(
            description = "Category name"
    )
    private String name;
    @Schema(
            description = "Category description"
    )
    private String description;

}
