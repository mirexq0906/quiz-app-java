package com.example.modules.module_folder.repository.impl;

import com.example.modules.module_folder.repository.ModuleFolderRepository;
import com.example.modules.modules.domain.Module;
import com.example.modules.modules.repository.mapper.ModuleRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ModuleFolderRepositoryImpl implements ModuleFolderRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String FIND_MODULES_BY_FOLDER_ID = """
            SELECT m.id, m.title, m.description, m.user_id, m.created_at, m.updated_at
            FROM modules AS m
            JOIN module_folder AS mf
            ON m.id = mf.module_id
            WHERE mf.folder_id = ?
            """;
    private final String CREATE = """
            INSERT INTO module_folder (module_id, folder_id)
            VALUES (?, ?)
            """;
    private final String DELETE = """
            DELETE FROM module_folder
            WHERE module_id = ? AND folder_id = ?
            """;

    @Override
    public List<Module> findModulesByFolderId(Long folderId) {
        return this.jdbcTemplate.query(
                this.FIND_MODULES_BY_FOLDER_ID,
                ModuleRowMapper::mapRow,
                folderId
        );
    }

    @Override
    public void create(Long moduleId, Long folderId) {
        this.jdbcTemplate.update(this.CREATE, moduleId, folderId);
    }

    @Override
    public void delete(Long moduleId, Long folderId) {
        this.jdbcTemplate.update(this.DELETE, moduleId, folderId);
    }

}
