package com.ai.account.common.core.user;

/**
 * 当前用户提供者（跨模块通用接口）
 */
public interface CurrentUserProvider {
    Long getCurrentUserId();
}

