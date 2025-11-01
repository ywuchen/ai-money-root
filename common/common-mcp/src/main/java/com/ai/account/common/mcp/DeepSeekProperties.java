package com.ai.account.common.mcp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DeepSeek 配置属性 POJO（通过 AutoConfiguration 提供 @Bean 绑定）
 * 外部配置统一使用小写中划线：deepseek.api-key、deepseek.base-url
 *
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Getter
@Setter
@ToString
public class DeepSeekProperties {
    private String apiKey;
    private String baseUrl;
}
