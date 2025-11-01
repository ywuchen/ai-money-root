package com.ai.account.record.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 记录服务启动类
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.ai.account")
public class RecordApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecordApplication.class, args);
    }
}

