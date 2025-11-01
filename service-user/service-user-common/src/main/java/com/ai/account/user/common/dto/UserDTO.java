package com.ai.account.user.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户传输对象，不暴露实体
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Getter
@Setter
@ToString
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String email;
    private String phone;
    private LocalDateTime createTime;
}

