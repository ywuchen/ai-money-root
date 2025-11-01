package com.ai.account.common.mybatis.handler;

import com.ai.account.common.core.user.CurrentUserProvider;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class AuditMetaObjectHandler implements MetaObjectHandler {

    private final CurrentUserProvider currentUserProvider;

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        strictInsertFill(metaObject, "create_time", LocalDateTime.class, now);
        strictInsertFill(metaObject, "update_time", LocalDateTime.class, now);
        Long uid = currentUserId();
        strictInsertFill(metaObject, "create_by", Long.class, uid);
        strictInsertFill(metaObject, "update_by", Long.class, uid);
        strictInsertFill(metaObject, "is_deleted", Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        strictUpdateFill(metaObject, "update_time", LocalDateTime.class, now);
        Long uid = currentUserId();
        strictUpdateFill(metaObject, "update_by", Long.class, uid);
    }

    protected Long currentUserId() {
        try {
            return currentUserProvider.getCurrentUserId();
        } catch (Exception e) {
            return null;
        }
    }
}
