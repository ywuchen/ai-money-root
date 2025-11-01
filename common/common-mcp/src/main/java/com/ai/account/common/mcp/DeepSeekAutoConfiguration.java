package com.ai.account.common.mcp;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * DeepSeek 自动配置
 * 通过 META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports 自动加载
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@AutoConfiguration
@EnableConfigurationProperties
public class DeepSeekAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "deepseek")
    public DeepSeekProperties deepSeekProperties() {
        return new DeepSeekProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public McpClient mcpClient(DeepSeekProperties properties) {
        return new McpClient(properties);
    }
}
