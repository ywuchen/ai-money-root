package com.ai.account.common.feign;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 通用配置
 * 所有 Feign 客户端应提供 FallbackFactory 并记录日志
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Slf4j
@Configuration
@EnableFeignClients
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}

