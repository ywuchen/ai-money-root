package com.ai.account.common.security.web;

import com.ai.account.common.security.context.UserContext;
import com.ai.account.common.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

/**
 * 简单的 JWT 解析过滤器：从 Authorization: Bearer <token> 解析用户信息
 * 将 userId、scope 存入线程上下文，供后续使用（如审计填充）
 * 注意：这不是完整的 Spring Security 鉴权实现，仅负责解析与上下文填充
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.hasText(auth) && auth.startsWith("Bearer ")) {
                String token = auth.substring(7);
                Map<String, Object> claims = jwtUtils.parse(token);
                String sub = (String) claims.get("sub");
                Object scope = claims.get("scope");
                Long userId = null;
                try { userId = Long.valueOf(sub); } catch (Exception ignore) {}
                UserContext.setUser(userId, scope == null ? null : scope.toString());
            }
            filterChain.doFilter(request, response);
        } finally {
            UserContext.clear();
        }
    }
}

