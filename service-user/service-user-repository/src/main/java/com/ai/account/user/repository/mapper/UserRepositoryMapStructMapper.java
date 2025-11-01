package com.ai.account.user.repository.mapper;

import com.ai.account.user.mapper.entity.UserEntity;
import com.ai.account.user.repository.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Entity <-> Domain 映射
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Mapper(componentModel = "spring")
public interface UserRepositoryMapStructMapper {
    @Mapping(target = "passwordHash", source = "password_hash")
    @Mapping(target = "createTime", source = "create_time")
    @Mapping(target = "updateTime", source = "update_time")
    User toDomain(UserEntity entity);

    @Mapping(target = "password_hash", source = "passwordHash")
    @Mapping(target = "create_time", source = "createTime")
    @Mapping(target = "update_time", source = "updateTime")
    UserEntity toEntity(User domain);
}

