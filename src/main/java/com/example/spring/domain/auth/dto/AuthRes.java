package com.example.spring.domain.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AuthRes {
    private String accessToken;

    private String refreshToken;

    private String tokenType = "Bearer";

    @Builder
    public AuthRes(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
