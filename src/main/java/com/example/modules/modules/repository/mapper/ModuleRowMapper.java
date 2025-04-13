package com.example.modules.modules.repository.mapper;

import com.example.modules.modules.domain.Module;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ModuleRowMapper {

    public static Module mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();

        return Module.builder()
                .id(resultSet.getLong("id"))
                .title(resultSet.getString("title"))
                .description(resultSet.getString("description"))
                .userId(resultSet.getLong("user_id"))
                .updatedAt(updatedAt)
                .createdAt(createdAt)
                .build();
    }

}
