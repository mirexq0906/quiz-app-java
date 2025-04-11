package com.example.modules.users.service.impl;

import com.example.modules.users.domain.User;
import com.example.modules.users.service.JwtService;
import com.example.modules.users.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final Duration accessToken = Duration.ofDays(2L);
    private final Duration refreshToken = Duration.ofDays(2L);
    private final String secret = "53A73E5F1C4E0A2D3B5F2D784E6A1B423D6F247D1F6E5C3A596D635A75327855";

    private final UserService userService;

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim(User.Fields.email, user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + this.accessToken.toMillis()))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secret)))
                .compact();
    }

    @Override
    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .claim(User.Fields.email, user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + this.refreshToken.toMillis()))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secret)))
                .compact();
    }

    @Override
    public boolean isValidToken(String token) {
        return !this.extractAllClaims(token).getExpiration().before(new Date());
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secret)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public Authentication getAuthentication(String token) {
        String username = this.extractAllClaims(token).getSubject();
        User user = (User) this.userService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );
    }

}
