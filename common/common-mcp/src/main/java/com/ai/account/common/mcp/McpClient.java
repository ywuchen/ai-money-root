package com.ai.account.common.mcp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * DeepSeek 简易 WebClient 封装
 * 实际可替换为官方 SDK
 * @author GitHub Copilot
 * @since 2025-11-01
 */
@Slf4j
@RequiredArgsConstructor
public class McpClient {
    private final DeepSeekProperties properties;

    public Mono<String> chatCompletions(String prompt) {
        WebClient client = WebClient.builder()
                .baseUrl(properties.getBaseUrl())
                .defaultHeader("Authorization", "Bearer " + properties.getApiKey())
                .build();
        return client.post().uri("/v1/chat/completions")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(Map.of("model", "deepseek-chat", "messages", new Object[]{Map.of("role", "user", "content", prompt)})))
                .retrieve()
                .bodyToMono(String.class);
    }
}
