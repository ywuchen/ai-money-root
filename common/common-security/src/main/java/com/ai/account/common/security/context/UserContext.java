package com.ai.account.common.security.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * 简单的线程上下文存储当前登录用户信息
 */
@Getter
@Setter
@ToString
@Component
public final class UserContext {
    private static final ThreadLocal<UserInfo> CTX = ThreadLocal.withInitial(UserInfo::new);

    public static void clear() { CTX.remove(); }
    public static UserInfo get() { return CTX.get(); }

    public static void setUser(Long userId, String scope) {
        UserInfo info = CTX.get();
        info.setUserId(userId);
        info.setScope(scope);
    }

    @Getter
    @Setter
    @ToString
    public static class UserInfo {
        private Long userId;
        private String scope;
    }
}
