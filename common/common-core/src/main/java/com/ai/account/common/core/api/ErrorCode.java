package com.ai.account.common.core.api;

import lombok.Getter;
import lombok.ToString;

/**
 * 错误码定义
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Getter
@ToString
public enum ErrorCode {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    SERVER_ERROR(500, "Internal Server Error"),
    UPSTREAM_ERROR(502, "Upstream Error"),
    PARSE_ERROR(460, "Parse Error");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

