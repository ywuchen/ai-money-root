package com.ai.account.notify.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 通知服务启动类
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.ai.account")
public class NotifyApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotifyApplication.class, args);
    }
}

