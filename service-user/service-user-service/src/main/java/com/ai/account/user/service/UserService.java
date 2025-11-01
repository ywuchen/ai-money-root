package com.ai.account.user.service;

import com.ai.account.user.common.dto.UserDTO;
import com.ai.account.user.common.vo.LoginRequestVO;
import com.ai.account.user.common.vo.RegisterRequestVO;
import com.ai.account.user.common.vo.TokenVO;
import com.ai.account.user.repository.model.User;
import com.ai.account.user.repository.UserRepository;
import com.ai.account.common.security.jwt.JwtUtils;
import com.ai.account.user.service.mapper.UserServiceMapStructMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * 用户用例编排
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserServiceMapStructMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public Optional<UserDTO> getById(Long id) {
        return userRepository.findById(id).map(mapper::toDTO);
    }

    @Transactional
    public UserDTO register(RegisterRequestVO vo) {
        User user = new User();
        user.setUsername(vo.getUsername());
        user.setEmail(vo.getEmail());
        user.setPasswordHash(passwordEncoder.encode(vo.getPassword()));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userRepository.save(user);
        log.info("USER_REGISTERED id={} email={}", user.getId(), user.getEmail());
        return mapper.toDTO(user);
    }

    public TokenVO login(LoginRequestVO vo) {
        // TODO: 按账号查询，演示用 1L
        Optional<UserDTO> opt = getById(1L);
        if (opt.isEmpty()) {
            throw new IllegalArgumentException("user not found");
        }
        String token = jwtUtils.generateToken(String.valueOf(opt.get().getId()), 3600, Map.of("scope", "user"));
        TokenVO t = new TokenVO();
        t.setAccessToken(token);
        t.setExpiresIn(3600);
        log.info("USER_LOGIN id={}", opt.get().getId());
        return t;
    }
}
