package com.example.spring.domain.auth.presentation;

import com.example.spring.domain.auth.application.AuthService;
import com.example.spring.domain.auth.dto.AuthRes;
import com.example.spring.domain.auth.dto.RefreshTokenReq;
import com.example.spring.domain.auth.dto.SignUpReq;
import com.example.spring.domain.auth.dto.SignInReq;
import com.example.spring.global.apiResponse.ApiResponse;
import com.example.spring.global.apiResponse.Message;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value="/sign-up")
    public ApiResponse<AuthRes> signUp(@RequestBody @Valid SignUpReq signUpReq
    ) {
        return ApiResponse.onSuccess(authService.signUp(signUpReq));
    }


    @PostMapping(value="/sign-in")
    public ApiResponse<AuthRes> signIn(@RequestBody @Valid SignInReq signInReq
    ) {
        return ApiResponse.onSuccess(authService.signIn(signInReq));
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthRes> reissue(@RequestBody RefreshTokenReq refreshTokenReq) {
        return ApiResponse.onSuccess(authService.refresh(refreshTokenReq));
    }

    @PostMapping(value="/sign-out")
    public ApiResponse<Message> signOut(@Valid @RequestBody RefreshTokenReq tokenRefreshRequest
    ) {
        return ApiResponse.onSuccess(authService.signOut(tokenRefreshRequest));
    }
}