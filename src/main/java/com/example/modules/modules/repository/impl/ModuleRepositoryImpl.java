package com.example.modules.modules.repository.impl;

import com.example.modules.modules.domain.Module;
import com.example.modules.modules.repository.ModuleRepository;
import com.example.modules.modules.repository.mapper.ModuleRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ModuleRepositoryImpl implements ModuleRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String FIND_ALL = """
            SELECT id, title, description, user_id, created_at, updated_at
            FROM modules
            """;

    private final String FIND_BY_ID = """
            SELECT id, title, description, user_id, created_at, updated_at
            FROM modules WHERE id = ?
            """;

    private final String CREATE = """
            INSERT INTO modules(title, description, user_id, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?)
            RETURNING id, title, description, user_id, created_at, updated_at
            """;

    private final String UPDATE = """
            UPDATE modules
            SET title = ?, description = ?, user_id = ?, updated_at = ?
            WHERE id = ?
            RETURNING id, title, description, user_id, created_at, updated_at
            """;

    private final String DELETE = """
            DELETE FROM modules
            WHERE id = ?
            """;

    @Override
    public List<Module> findAll() {
        return this.jdbcTemplate.query(
                this.FIND_ALL,
                ModuleRowMapper::mapRow
        );
    }

    @Override
    public Optional<Module> findById(Long id) {
        Module module = this.jdbcTemplate.queryForObject(
                this.FIND_BY_ID,
                ModuleRowMapper::mapRow,
                id
        );
        return Optional.ofNullable(module);
    }

    @Override
    public Module create(Module module) {
        return this.jdbcTemplate.queryForObject(
                this.CREATE,
                ModuleRowMapper::mapRow,
                module.getId(), module.getTitle(), module.getDescription(),
                module.getUserId(), module.getCreatedAt(), module.getUpdatedAt()
        );
    }

    @Override
    public Module update(Module module) {
        return this.jdbcTemplate.queryForObject(
                this.UPDATE,
                ModuleRowMapper::mapRow,
                module.getTitle(), module.getDescription(), module.getUserId(),
                module.getCreatedAt(), module.getUpdatedAt(), module.getId()
        );
    }

    @Override
    public void delete(Module module) {
        this.jdbcTemplate.update(this.DELETE);
    }

}
