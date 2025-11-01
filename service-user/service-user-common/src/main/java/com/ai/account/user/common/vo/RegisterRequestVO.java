package com.ai.account.user.common.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 注册请求 VO
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Getter
@Setter
@ToString
public class RegisterRequestVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotBlank
    private String password;
}

