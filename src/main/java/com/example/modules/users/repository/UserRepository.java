package com.example.modules.users.repository;

import com.example.modules.users.domain.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    User create(User user);

    User update(User user);

}
