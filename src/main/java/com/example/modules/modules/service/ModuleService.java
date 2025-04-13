package com.example.modules.modules.service;

import com.example.modules.modules.domain.Module;
import java.util.List;

public interface ModuleService {

    List<Module> findAll();

    Module findById(Long id);

    Module create(Module module);

    Module update(Module module);

    void delete(Module module);

}
