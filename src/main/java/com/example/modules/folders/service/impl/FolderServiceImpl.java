package com.example.modules.folders.service.impl;

import com.example.exception.DomainNotFoundException;
import com.example.modules.folders.domain.Folder;
import com.example.modules.folders.repository.FolderRepository;
import com.example.modules.folders.service.FolderService;
import com.example.modules.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;

    @Override
    public List<Folder> findAll() {
        return this.folderRepository.findAll();
    }

    @Override
    public Folder findById(Long id) {
        return this.folderRepository.findById(id)
                .orElseThrow(() -> new DomainNotFoundException("Folder not found"));
    }

    @Override
    public Folder create(Folder folder) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        folder.setUserId(user.getId());
        return this.folderRepository.create(folder);
    }

    @Override
    public Folder update(Folder folder) {
        Folder updatedFolder = this.findById(folder.getId());
        Optional.ofNullable(folder.getTitle()).ifPresent(updatedFolder::setTitle);
        Optional.ofNullable(folder.getDescription()).ifPresent(updatedFolder::setDescription);
        return this.folderRepository.update(updatedFolder);
    }

    @Override
    public void delete(Long id) {
        this.folderRepository.delete(id);
    }

}
