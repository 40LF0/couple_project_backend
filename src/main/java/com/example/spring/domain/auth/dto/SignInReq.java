package com.example.spring.domain.auth.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@Data
public class SignInReq {

    @Email
    private String email;

    private String providerId;

}
