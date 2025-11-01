package com.ai.account.mcp.bridge.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MCP Bridge 启动类
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.ai.account")
public class McpBridgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(McpBridgeApplication.class, args);
    }
}

