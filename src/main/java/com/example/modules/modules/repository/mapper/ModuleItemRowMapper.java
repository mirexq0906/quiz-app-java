package com.example.modules.modules.repository.mapper;

import com.example.modules.modules.domain.ModuleItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ModuleItemRowMapper {

    public static ModuleItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();

        return ModuleItem.builder()
                .id(resultSet.getLong("id"))
                .title(resultSet.getString("title"))
                .description(resultSet.getString("description"))
                .code(resultSet.getString("code"))
                .moduleId(resultSet.getLong("module_id"))
                .updatedAt(updatedAt)
                .createdAt(createdAt)
                .build();
    }

}
