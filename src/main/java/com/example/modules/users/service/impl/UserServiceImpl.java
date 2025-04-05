package com.example.modules.users.service.impl;

import com.example.modules.users.domain.User;
import com.example.modules.users.repository.UserRepository;
import com.example.modules.users.service.UserService;
import com.example.modules.users.web.dto.UserDto;
import com.example.modules.users.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User update(UserDto userDto, Long id) {
        User user = this.userMapper.requestToUser(userDto, id);
        User updatedUser = this.findById(id);
        Optional.ofNullable(user.getUsername()).ifPresent(updatedUser::setUsername);
        Optional.ofNullable(user.getPassword()).ifPresent((item) -> {
            updatedUser.setPassword(this.passwordEncoder.encode(item));
        });
        Optional.ofNullable(user.getEmail()).ifPresent(updatedUser::setEmail);

        return this.userRepository.update(updatedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
