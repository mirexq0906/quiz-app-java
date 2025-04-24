package com.example.modules.modules.service.impl;

import com.example.exception.DomainNotFoundException;
import com.example.modules.modules.domain.Module;
import com.example.modules.modules.repository.ModuleRepository;
import com.example.modules.modules.service.ModuleService;
import com.example.modules.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;

    @Override
    public List<Module> findAll() {
        return this.moduleRepository.findAll();
    }

    @Override
    public Module findById(Long id) {
        return this.moduleRepository.findById(id)
                .orElseThrow(() -> new DomainNotFoundException("Module not found"));
    }

    @Override
    public Module create(Module module) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        module.setUserId(user.getId());
        return this.moduleRepository.create(module);
    }

    @Override
    public Module update(Module module) {
        Module updatedModule = this.findById(module.getId());
        Optional.ofNullable(module.getTitle()).ifPresent(updatedModule::setTitle);
        Optional.ofNullable(module.getDescription()).ifPresent(updatedModule::setDescription);
        return this.moduleRepository.update(updatedModule);
    }

    @Override
    public void delete(Long id) {
        this.moduleRepository.delete(id);
    }

}
