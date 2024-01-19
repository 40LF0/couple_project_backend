package com.example.spring.domain.auth.application;

import com.example.spring.domain.auth.domain.Token;
import com.example.spring.domain.auth.domain.repository.TokenRepository;
import com.example.spring.domain.auth.dto.*;
import com.example.spring.domain.auth.exception.AlreadyExistEmailException;
import com.example.spring.domain.user.domain.Member;
import com.example.spring.domain.user.domain.Role;
import com.example.spring.domain.user.domain.repository.MemberRepository;
import com.example.spring.domain.auth.dto.SignInReq;
import com.example.spring.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.domain.user.exception.*;
import com.example.spring.global.DefaultAssert;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthService {
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private final TokenRepository tokenRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public AuthRes signUp(final SignUpReq signUpReq) {
        if (memberRepository.existsByEmail(signUpReq.getEmail()))
            throw new AlreadyExistEmailException();

        Member newUser = Member.builder()
                .providerId(signUpReq.getProviderId())
                .provider("kakao")
                .name(signUpReq.getNickname())
                .email(signUpReq.getEmail())
                .profileUrl(signUpReq.getProfileImgUrl())
                .role(Role.USER)
                .build();

        memberRepository.save(newUser);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signUpReq.getEmail(),
                        signUpReq.getProviderId()
                )
        );

        TokenMapping tokenMapping = tokenProvider.createToken(authentication);
        Token token = Token.builder()
                .refreshToken(tokenMapping.getRefreshToken())
                .userEmail(tokenMapping.getUserEmail())
                .build();
        tokenRepository.save(token);

        return AuthRes.builder()
                .accessToken(tokenMapping.getAccessToken())
                .refreshToken(tokenMapping.getRefreshToken())
                .build();
    }

    @Transactional
    public AuthRes signIn(final SignInReq signInReq) {
        Member member = memberRepository.findByEmail(signInReq.getEmail())
                .orElseThrow(InvalidUserException::new);
        if (!member.getProviderId().equals(signInReq.getProviderId())) {
            throw new InvalidUserException();
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInReq.getEmail(),
                        signInReq.getProviderId()
                )
        );

        TokenMapping tokenMapping = tokenProvider.createToken(authentication);
        Token token = Token.builder()
                .refreshToken(tokenMapping.getRefreshToken())
                .userEmail(tokenMapping.getUserEmail())
                .build();

        tokenRepository.save(token);

        return AuthRes.builder()
                .accessToken(tokenMapping.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

//    @Transactional
//    public AuthRes refresh(final RefreshTokenReq tokenRefreshRequest) {
//        //1차 검증
//        boolean checkValid = valid(tokenRefreshRequest.getRefreshToken());
//        DefaultAssert.isAuthentication(checkValid);
//
//        Token token = tokenRepository.findByRefreshToken(tokenRefreshRequest.getRefreshToken())
//                .orElseThrow(() -> new DefaultAuthenticationException(ErrorCode.INVALID_AUTHENTICATION));
//        Authentication authentication = tokenProvider.getAuthenticationByEmail(token.getUserEmail());
//
//        //4. refresh token 정보 값을 업데이트 한다.
//        //시간 유효성 확인
//        TokenMapping tokenMapping;
//
//        Long expirationTime = tokenProvider.getExpiration(tokenRefreshRequest.getRefreshToken());
//        if (expirationTime > 0) {
//            tokenMapping = tokenProvider.refreshToken(authentication, token.getRefreshToken());
//        } else {
//            tokenMapping = tokenProvider.createToken(authentication);
//        }
//
//        Token updateToken = token.updateRefreshToken(tokenMapping.getRefreshToken());
//        tokenRepository.save(updateToken);
//
//        AuthRes authResponse = AuthRes.builder().accessToken(tokenMapping.getAccessToken()).refreshToken(updateToken.getRefreshToken()).build();
//
//        return authResponse;
//    }

//    @Transactional
//    public Message signOut(final RefreshTokenReq tokenRefreshRequest) {
//        Token token = tokenRepository.findByRefreshToken(tokenRefreshRequest.getRefreshToken())
//                .orElseThrow(() -> new DefaultAuthenticationException(ErrorCode.INVALID_AUTHENTICATION));
//        tokenRepository.delete(token);
//
//        return Message.builder()
//                .message("로그아웃 하였습니다.")
//                .build();
//    }

//    private boolean valid(final String refreshToken) {
//        //1. 토큰 형식 물리적 검증
//        boolean validateCheck = tokenProvider.validateToken(refreshToken);
//        DefaultAssert.isTrue(validateCheck, "Token 검증에 실패하였습니다.");
//
//        //2. refresh token 값을 불러온다.
//        Optional<Token> token = tokenRepository.findByRefreshToken(refreshToken);
//        DefaultAssert.isTrue(token.isPresent(), "탈퇴 처리된 회원입니다.");
//
//        //3. email 값을 통해 인증값을 불러온다
//        Authentication authentication = tokenProvider.getAuthenticationByEmail(token.get().getUserEmail());
//        DefaultAssert.isTrue(token.get().getUserEmail().equals(authentication.getName()), "사용자 인증에 실패하였습니다.");
//
//        return true;
//    }

}