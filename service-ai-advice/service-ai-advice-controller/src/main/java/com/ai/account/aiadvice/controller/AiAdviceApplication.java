package com.ai.account.aiadvice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * AI 建议服务启动类
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.ai.account")
public class AiAdviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AiAdviceApplication.class, args);
    }
}

