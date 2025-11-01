package com.ai.account.common.security.config;

import com.ai.account.common.security.jwt.JwtUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全相关通用配置
 * 外部配置前缀 security.jwt-secret
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Configuration
@EnableConfigurationProperties(JwtConfig.JwtSecretProperties.class)
public class JwtConfig {

    @Bean
    public JwtUtils jwtUtils(JwtSecretProperties props) {
        return new JwtUtils(props.getJwtSecret());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Getter
    @Setter
    @ToString
    @ConfigurationProperties(prefix = "security")
    public static class JwtSecretProperties {
        private String jwtSecret = "dev-secret-please-change";
    }
}

