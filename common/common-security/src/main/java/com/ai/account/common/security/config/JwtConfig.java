package com.ai.account.common.security.config;

import com.ai.account.common.security.jwt.JwtUtils;
import com.ai.account.common.security.web.JwtAuthenticationFilter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

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

    // 注册简单的 JWT 解析过滤器，顺序可按需调整
    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilterRegistration(JwtUtils jwtUtils) {
        FilterRegistrationBean<JwtAuthenticationFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new JwtAuthenticationFilter(jwtUtils));
        bean.setOrder(-101); // 早于多数业务过滤器
        bean.addUrlPatterns("/*");
        return bean;
    }

    @Getter
    @Setter
    @ToString
    @ConfigurationProperties(prefix = "security")
    public static class JwtSecretProperties {
        private String jwtSecret = "dev-secret-please-change";
    }
}
