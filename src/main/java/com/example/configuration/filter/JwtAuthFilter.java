package com.example.configuration.filter;

import com.example.modules.users.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String bearerToken = request.getHeader(HEADER_NAME);

        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            bearerToken = bearerToken.substring(BEARER_PREFIX.length());
        }

        if (bearerToken != null && this.jwtService.isValidToken(bearerToken)) {
            SecurityContextHolder.getContext().setAuthentication(
                    this.jwtService.getAuthentication(bearerToken)
            );
        }

        filterChain.doFilter(request, response);
    }
}
