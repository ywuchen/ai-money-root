package com.ai.account.user.common.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * token 返回 VO
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Getter
@Setter
@ToString
public class TokenVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accessToken;
    private String tokenType = "Bearer";
    private long expiresIn;
}

