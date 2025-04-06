package com.example.modules.users.service;

import com.example.modules.users.domain.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface JwtService {

    String generateToken(User user);

    String generateRefreshToken(User user);

    boolean isValidToken(String token);

    Claims extractAllClaims(String token);

    Authentication getAuthentication(String token);

}
