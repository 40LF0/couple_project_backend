package com.example.spring.domain.auth.presentation;

import com.example.spring.domain.auth.application.AuthService;
import com.example.spring.domain.auth.dto.AuthRes;
import com.example.spring.domain.auth.dto.RefreshTokenReq;
import com.example.spring.domain.auth.dto.SignUpReq;
import com.example.spring.domain.auth.dto.SignInReq;
import com.example.spring.global.payload.ResponseCustom;
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
    public ResponseCustom<AuthRes> signUp(@RequestBody SignUpReq signUpReq
    ) {
        return ResponseCustom.OK(authService.signUp(signUpReq));
    }


    @PostMapping(value="/sign-in")
    public ResponseCustom<AuthRes> signIn(@RequestBody SignInReq signInReq
    ) {
        return ResponseCustom.OK(authService.signIn(signInReq));
    }

    @PostMapping("/refresh")
    public ResponseCustom<AuthRes> reissue(@RequestBody RefreshTokenReq refreshTokenReq) {
        return ResponseCustom.OK(authService.refresh(refreshTokenReq));
    }


//    @PostMapping(value="/sign-out")
//    public ResponseCustom<Message> signOut(@Valid @RequestBody RefreshTokenReq tokenRefreshRequest
//    ) {
//        return ResponseCustom.OK(authService.signOut(tokenRefreshRequest));
//    }
}