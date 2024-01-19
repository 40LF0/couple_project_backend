package com.example.spring.domain.auth.exception;

public class TimeOutException extends RuntimeException{

    public TimeOutException() {
        super("시간초과 되었습니다.");
    }

}