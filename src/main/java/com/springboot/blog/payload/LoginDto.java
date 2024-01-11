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
        description = "Login Model Information"
)
public class LoginDto {

    @Schema(
            description = "User email or username"
    )
    private String usernameOrEmail;
    @Schema(
            description = "User password"
    )
    private String password;

}
