package com.ai.account.common.security.autoconfigure;

import com.ai.account.common.security.config.JwtConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * Security 自动配置：注册 JwtUtils、PasswordEncoder 等
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@AutoConfiguration
@Import({JwtConfig.class})
public class SecurityAutoConfiguration {
}

