package com.example.modules.modules.repository;

import com.example.modules.modules.domain.Module;

import java.util.List;
import java.util.Optional;

public interface ModuleRepository {

    Optional<Module> findById(Long id);

    List<Module> findAll();

    Module create(Module module);

    Module update(Module module);

    void delete(Module module);

}
