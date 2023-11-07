package com.example.demo.dto.Login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class TokenInfo{
        private String grantType;
        private String accessToken;
        private Long accessTokenExpiresIn;
        private String refreshToken;
    }
}
