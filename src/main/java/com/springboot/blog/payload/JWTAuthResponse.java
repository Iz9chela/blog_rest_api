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
        description = "JWT token response after login"
)
public class JWTAuthResponse {

    @Schema(
            description = "Coded user information and secret key"
    )
    private String accessToken;
    @Schema(
            description = "Start of the jwt token"
    )
    private String tokenType = "Bearer";


}
