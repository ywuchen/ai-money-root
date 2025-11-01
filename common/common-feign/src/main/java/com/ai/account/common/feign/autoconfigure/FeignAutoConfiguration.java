package com.ai.account.common.feign.autoconfigure;

import com.ai.account.common.feign.FeignConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * Feign 自动配置：通用 Feign 日志级别与 EnableFeignClients
 * 注意：具体的 Feign Client 仍需在使用方模块声明接口与 FallbackFactory。
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@AutoConfiguration
@Import({FeignConfig.class})
public class FeignAutoConfiguration {
}

