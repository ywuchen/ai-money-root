package com.ai.account.user.repository.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户领域模型（仓储向上暴露）
 * 不包含持久化细节
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Getter
@Setter
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String passwordHash;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

