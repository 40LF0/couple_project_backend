package com.example.spring.global.log;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogUtil {

    public static void writeErrorLog(Exception e){
        log.error(" {} {} {} ", e.getCause(),e.getClass(),e.getMessage());
    }

    public static void writeErrorInfoLog(Exception e, String message){
        log.info(" {} {} {} {}", e.getCause(),e.getClass(),e.getMessage(),message);
    }

}
