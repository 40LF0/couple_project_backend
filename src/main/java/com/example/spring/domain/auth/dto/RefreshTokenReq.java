package com.example.spring.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenReq {

    @NotBlank
    private String refreshToken;

}
