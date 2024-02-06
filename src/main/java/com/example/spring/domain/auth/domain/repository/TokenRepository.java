package com.example.spring.domain.auth.domain.repository;

import com.example.spring.domain.auth.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByUserEmail(String userEmail);
    Optional<Token> findByRefreshToken(String refreshToken);
}