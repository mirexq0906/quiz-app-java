package com.example.modules.modules.repository.impl;

import com.example.modules.modules.domain.ModuleItem;
import com.example.modules.modules.repository.ModuleItemRepository;
import com.example.modules.modules.repository.mapper.ModuleItemRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ModuleItemRepositoryImpl implements ModuleItemRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ModuleItem> findModuleItemsByModuleId(Long moduleId) {
        return this.jdbcTemplate.query(
                """
                        SELECT id, title, description, code, module_id, created_at, updated_at
                        FROM module_items
                        WHERE module_id = ?
                        """,
                ModuleItemRowMapper::mapRow,
                moduleId
        );
    }

    @Override
    public Optional<ModuleItem> findModuleItemById(Long id) {
        ModuleItem moduleItem = this.jdbcTemplate.queryForObject(
                """
                        SELECT id, title, description, code, module_id, created_at, updated_at
                        FROM module_items
                        WHERE id = ?
                        """,
                ModuleItemRowMapper::mapRow,
                id
        );
        return Optional.ofNullable(moduleItem);
    }

    @Override
    public ModuleItem addItemToModule(ModuleItem moduleItem) {
        return this.jdbcTemplate.queryForObject(
                """
                        INSERT INTO module_items(title, description, code, module_id) 
                        VALUES (?, ?, ?, ?)
                        RETURNING id, title, description, code, module_id, created_at, updated_at
                        """,
                ModuleItemRowMapper::mapRow,
                moduleItem.getTitle(), moduleItem.getDescription(), moduleItem.getCode(), moduleItem.getModuleId()
        );
    }

    @Override
    public ModuleItem updateItemToModule(ModuleItem moduleItem) {
        return this.jdbcTemplate.queryForObject(
                """
                        UPDATE module_items
                        SET title = ?, description = ?, code = ?
                        WHERE id = ?
                        RETURNING id, title, description, code, module_id, created_at, updated_at
                        """,
                ModuleItemRowMapper::mapRow,
                moduleItem.getTitle(), moduleItem.getDescription(), moduleItem.getCode(), moduleItem.getId()
        );
    }

    @Override
    public void removeItemFromModule(Long moduleItemId) {
        this.jdbcTemplate.update(
                """
                        DELETE FROM module_items
                        WHERE id = ?;
                        """,
                moduleItemId
        );
    }

    @Override
    public int count() {
        return this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM module_items", Integer.class);
    }

}
