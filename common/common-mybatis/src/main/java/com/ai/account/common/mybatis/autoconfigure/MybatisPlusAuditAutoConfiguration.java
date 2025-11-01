package com.ai.account.common.mybatis.autoconfigure;

import com.ai.account.common.core.user.CurrentUserProvider;
import com.ai.account.common.mybatis.handler.AuditMetaObjectHandler;
import com.ai.account.common.security.context.UserContext;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class MybatisPlusAuditAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(CurrentUserProvider.class)
    public CurrentUserProvider defaultCurrentUserProvider() {
        return () -> UserContext.get().getUserId();
    }

    @Bean
    public AuditMetaObjectHandler auditMetaObjectHandler(CurrentUserProvider currentUserProvider) {
        return new AuditMetaObjectHandler(currentUserProvider);
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
}
