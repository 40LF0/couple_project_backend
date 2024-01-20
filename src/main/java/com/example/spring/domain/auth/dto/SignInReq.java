package com.example.spring.domain.auth.dto;

import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.member.enums.Role;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class SignInReq {

    @Email
    private String email;

    private String providerId;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .providerId(passwordEncoder.encode(providerId))
                .role(Role.USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, providerId);
    }
}
