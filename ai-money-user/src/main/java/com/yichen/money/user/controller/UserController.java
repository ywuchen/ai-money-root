package com.yichen.money.user.controller;

import com.yichen.money.user.config.dto.LoginRequest;
import com.yichen.money.user.config.dto.RegisterRequest;
import com.yichen.money.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/register")
    public Mono<String> register(@RequestBody RegisterRequest req) {
        return service.register(req);
    }

    @PostMapping("/login")
    public Mono<Map<String,String>> login(@RequestBody LoginRequest req) {
        return service.login(req).map(token -> Map.of("token", token));
    }
}