package com.ai.account.common.core.autoconfigure;

import com.ai.account.common.core.exception.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * Core 自动配置：注册全局异常处理等通用组件
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@AutoConfiguration
@Import({GlobalExceptionHandler.class})
public class CoreAutoConfiguration {
}

