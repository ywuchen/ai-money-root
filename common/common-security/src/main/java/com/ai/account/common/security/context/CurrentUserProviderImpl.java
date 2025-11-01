package com.ai.account.common.security.context;

import com.ai.account.common.core.user.CurrentUserProvider;
import org.springframework.stereotype.Component;

/**
 * 使用 UserContext 实现 CurrentUserProvider
 */
@Component
public class CurrentUserProviderImpl implements CurrentUserProvider {
    @Override
    public Long getCurrentUserId() {
        return UserContext.get().getUserId();
    }
}
