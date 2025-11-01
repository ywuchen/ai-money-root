package com.ai.account.user.repository;

import com.ai.account.user.mapper.UserMapper;
import com.ai.account.user.mapper.entity.UserEntity;
import com.ai.account.user.repository.mapper.UserRepositoryMapStructMapper;
import com.ai.account.user.repository.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 仓储封装数据访问
 * 不向上暴露框架类型
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserMapper userMapper;
    private final UserRepositoryMapStructMapper mapper;

    public Optional<User> findById(Long id) {
        UserEntity e = userMapper.selectById(id);
        return Optional.ofNullable(e).map(mapper::toDomain);
    }

    public void save(User user) {
        UserEntity entity = mapper.toEntity(user);
        if (entity.getId() == null) {
            userMapper.insert(entity);
            user.setId(entity.getId());
        } else {
            userMapper.updateById(entity);
        }
    }
}
