package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "Register Model Information"
)
public class RegisterDto {

    @Schema(
            description = "Register User name"
    )
    private String name;
    @Schema(
            description = "Register User username"
    )
    private String username;
    @Schema(
            description = "Register User email"
    )
    private String email;
    @Schema(
            description = "Register User password"
    )
    private String password;

}
