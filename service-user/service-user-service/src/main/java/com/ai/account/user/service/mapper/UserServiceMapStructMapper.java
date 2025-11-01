package com.ai.account.user.service.mapper;

import com.ai.account.user.common.dto.UserDTO;
import com.ai.account.user.repository.model.User;
import org.mapstruct.Mapper;

/**
 * Domain -> DTO 映射
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Mapper(componentModel = "spring")
public interface UserServiceMapStructMapper {
    UserDTO toDTO(User user);
}

