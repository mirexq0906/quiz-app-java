package com.example.modules.users.service.impl;

import com.example.modules.users.domain.User;
import com.example.modules.users.repository.UserRepository;
import com.example.modules.users.service.AuthService;
import com.example.modules.users.service.JwtService;
import com.example.modules.users.web.dto.UserDto;
import com.example.modules.users.web.mapper.UserMapper;
import com.example.modules.users.web.response.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public JwtResponse login(UserDto userDto) {
        Authentication authenticate = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getUsername(),
                        userDto.getPassword()
                )
        );

        User user = (User) authenticate.getPrincipal();

        return JwtResponse.builder()
                .accessToken(jwtService.generateToken(user))
                .refreshToken(jwtService.generateRefreshToken(user))
                .build();
    }

    @Override
    public User register(UserDto userDto) {
        User user = this.userMapper.requestToUser(userDto);
        user.setPassword(
                this.passwordEncoder.encode(user.getPassword())
        );

        return this.userRepository.create(user);
    }

}
