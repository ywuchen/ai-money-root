package com.ai.account.user.common.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 登录请求 VO
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Getter
@Setter
@ToString
public class LoginRequestVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotBlank
    private String account; // 手机/邮箱/用户名
    @NotBlank
    private String password;
}

