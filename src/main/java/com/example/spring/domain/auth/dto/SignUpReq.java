package com.example.spring.domain.auth.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class SignUpReq {

    private String providerId;

    private String nickname;

    @Email
    private String email;

    private String profileImgUrl;

}
