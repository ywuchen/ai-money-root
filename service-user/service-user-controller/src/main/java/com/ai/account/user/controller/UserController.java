package com.ai.account.user.controller;

import com.ai.account.common.core.api.ErrorCode;
import com.ai.account.common.core.api.Result;
import com.ai.account.user.common.dto.UserDTO;
import com.ai.account.user.common.vo.LoginRequestVO;
import com.ai.account.user.common.vo.RegisterRequestVO;
import com.ai.account.user.common.vo.TokenVO;
import com.ai.account.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口，仅编排请求/响应，固定返回 Result<T>
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public Result<UserDTO> register(@Valid @RequestBody RegisterRequestVO vo) {
        UserDTO dto = userService.register(vo);
        return Result.ok(dto);
    }

    @PostMapping("/login")
    public Result<TokenVO> login(@Valid @RequestBody LoginRequestVO vo) {
        try {
            TokenVO token = userService.login(vo);
            return Result.ok(token);
        } catch (IllegalArgumentException e) {
            log.warn("LOGIN_FAIL msg={}", e.getMessage());
            return Result.fail(ErrorCode.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Result<UserDTO> getById(@PathVariable Long id) {
        return userService.getById(id)
                .map(Result::ok)
                .orElseGet(() -> Result.fail(ErrorCode.NOT_FOUND));
    }
}

