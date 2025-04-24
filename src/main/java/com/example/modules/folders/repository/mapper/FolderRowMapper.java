package com.example.modules.folders.repository.mapper;

import com.example.modules.folders.domain.Folder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class FolderRowMapper {

    public static Folder mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();

        return Folder.builder()
                .id(resultSet.getLong("id"))
                .title(resultSet.getString("title"))
                .description(resultSet.getString("description"))
                .userId(resultSet.getLong("user_id"))
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
