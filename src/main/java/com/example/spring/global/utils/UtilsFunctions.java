package com.example.spring.global.utils;

public class UtilsFunctions {
    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds* 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
