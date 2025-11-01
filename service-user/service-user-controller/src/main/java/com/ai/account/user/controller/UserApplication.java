package com.ai.account.user.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用户服务启动类
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@SpringBootApplication(scanBasePackages = "com.ai.account")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}

