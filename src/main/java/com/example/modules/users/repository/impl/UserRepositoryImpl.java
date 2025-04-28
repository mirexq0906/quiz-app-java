package com.example.modules.users.repository.impl;

import com.example.modules.users.domain.User;
import com.example.modules.users.repository.UserRepository;
import com.example.modules.users.repository.mapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String FIND_BY_ID = """
            SELECT id, username, email, password, role, created_at, updated_at
            FROM users
            WHERE id = ?
            """;

    private final String FIND_BY_USERNAME = """
            SELECT id, username, email, password, role, created_at, updated_at
            FROM users
            WHERE username = ?
            """;

    private final String CREATE = """
            INSERT INTO users (username, email, password, role)
            VALUES (?, ?, ?, ?)
            RETURNING id, username, email, password, role, created_at, updated_at
            """;

    private final String UPDATE = """
            UPDATE users
            SET username = ?, email = ?, password = ?, role = ?
            WHERE id = ?
            RETURNING id, username, email, password, role, created_at, updated_at
            """;

    private final String COUNT = "SELECT COUNT(*) FROM users";

    @Override
    public Optional<User> findById(Long id) {
        User user = this.jdbcTemplate.queryForObject(
                this.FIND_BY_ID,
                UserRowMapper::mapRow,
                id
        );

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = this.jdbcTemplate.queryForObject(
                this.FIND_BY_USERNAME,
                UserRowMapper::mapRow,
                username
        );

        return Optional.ofNullable(user);
    }

    @Override
    public User create(User user) {
        return this.jdbcTemplate.queryForObject(
                this.CREATE,
                UserRowMapper::mapRow,
                user.getUsername(), user.getEmail(),
                user.getPassword(), user.getRole().toString()
        );
    }

    @Override
    public User update(User user) {
        return this.jdbcTemplate.queryForObject(
                this.UPDATE,
                UserRowMapper::mapRow,
                user.getUsername(), user.getEmail(),
                user.getPassword(), user.getRole().toString(),
                user.getId()
        );
    }

    @Override
    public Integer count() {
        return this.jdbcTemplate.queryForObject(
                this.COUNT, Integer.class
        );
    }

}
