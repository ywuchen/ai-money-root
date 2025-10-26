package com.yichen.money.user.service;

import com.yichen.money.user.config.dto.LoginRequest;
import com.yichen.money.user.config.dto.RegisterRequest;
import com.yichen.money.user.model.User;
import com.yichen.money.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public Mono<String> register(RegisterRequest req) {
        return repo.save(new User()
                        .setUsername(req.getUsername())
                        .setPassword(encoder.encode(req.getPassword())))
                .then(Mono.just("ok"))
                .onErrorResume(DuplicateKeyException.class,
                        e -> Mono.error(new ResponseStatusException(CONFLICT, "用户已存在")));
    }

    public Mono<String> login(LoginRequest req) {
        return repo.findByUsername(req.getUsername())
                .switchIfEmpty(Mono.error(new ResponseStatusException(UNAUTHORIZED, "账号或密码错误")))
                .filter(u -> encoder.matches(req.getPassword(), u.getPassword()))
                .switchIfEmpty(Mono.error(new ResponseStatusException(UNAUTHORIZED, "账号或密码错误")))
                .map(u -> jwtService.generate(u.getUsername()));
    }
}