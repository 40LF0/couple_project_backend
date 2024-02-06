package com.example.spring.domain.auth.dto;

import com.example.spring.domain.member.domain.Member;
import com.example.spring.domain.member.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class SignUpReq {
    @NotBlank
    private String providerId;
    @NotBlank
    private String nickname;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String profileImgUrl;

    @NotBlank
    private String provider;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .providerId(passwordEncoder.encode(providerId))
                .provider(provider)
                .role(Role.USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, providerId);
    }

}
