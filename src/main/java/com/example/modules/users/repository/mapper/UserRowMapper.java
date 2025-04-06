package com.example.modules.users.repository.mapper;

import com.example.modules.users.domain.Role;
import com.example.modules.users.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserRowMapper {

    public static User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Timestamp createdAt = resultSet.getTimestamp("created_at");
        Timestamp updatedAt = resultSet.getTimestamp("updated_at");

        return User.builder()
                .id(resultSet.getLong("id"))
                .username(resultSet.getString("username"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .role(Role.valueOf(resultSet.getString("role")))
                .createTime(createdAt.toLocalDateTime())
                .updateTime(updatedAt.toLocalDateTime())
                .build();
    }
}
