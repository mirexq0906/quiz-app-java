package com.example.modules.folders.repository.impl;

import com.example.modules.folders.domain.Folder;
import com.example.modules.folders.repository.FolderRepository;
import com.example.modules.folders.repository.mapper.FolderRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FolderRepositoryImpl implements FolderRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String FIND_ALL = """
            SELECT id, title, description, user_id, created_at, updated_at
            FROM folders
            """;

    private final String FIND_BY_ID = """
            SELECT id, title, description, user_id, created_at, updated_at
            FROM folders
            WHERE id = ?
            """;

    private final String CREATE = """
            INSERT INTO folders(title, description, user_id)
            VALUES (?, ?, ?)
            RETURNING id, title, description, user_id, created_at, updated_at;
            """;

    private final String UPDATE = """
            UPDATE folders
            SET title = ?, description = ?
            WHERE id = ?
            RETURNING id, title, description, user_id, created_at, updated_at;
            """;

    private final String DELETE = """
            DELETE FROM folders
            WHERE id = ?
            """;

    private final String COUNT = """
            SELECT COUNT(*) FROM folders
            """;

    @Override
    public List<Folder> findAll() {
        return this.jdbcTemplate.query(
                this.FIND_ALL,
                FolderRowMapper::mapRow
        );
    }

    @Override
    public Optional<Folder> findById(Long id) {
        Folder folder = this.jdbcTemplate.queryForObject(
                this.FIND_BY_ID,
                FolderRowMapper::mapRow,
                id
        );

        return Optional.ofNullable(folder);
    }

    @Override
    public Folder create(Folder folder) {
        return this.jdbcTemplate.queryForObject(
                this.CREATE,
                FolderRowMapper::mapRow,
                folder.getTitle(), folder.getDescription(), folder.getUserId()
        );
    }

    @Override
    public Folder update(Folder folder) {
        return this.jdbcTemplate.queryForObject(
                this.UPDATE,
                FolderRowMapper::mapRow,
                folder.getTitle(), folder.getDescription(),
                folder.getId()
        );
    }

    @Override
    public void delete(Long id) {
        this.jdbcTemplate.update(this.DELETE, id);
    }

    @Override
    public Integer count() {
        return this.jdbcTemplate.queryForObject(
                this.COUNT, Integer.class
        );
    }

}
