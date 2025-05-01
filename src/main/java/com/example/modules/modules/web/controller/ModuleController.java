package com.example.modules.modules.web.controller;

import com.example.modules.module_folder.service.ModuleFolderService;
import com.example.modules.modules.service.FileProcessorService;
import com.example.modules.modules.service.ModuleService;
import com.example.modules.modules.web.dto.ModuleDto;
import com.example.modules.modules.web.mapper.ModuleMapper;
import com.example.modules.modules.web.response.ModuleListResponse;
import com.example.modules.modules.web.response.ModuleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/module")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;
    private final ModuleFolderService moduleFolderService;
    private final FileProcessorService fileProcessorService;
    private final ModuleMapper moduleMapper;

    @GetMapping
    public ResponseEntity<ModuleListResponse> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.moduleMapper.moduleListToResponse(
                        this.moduleService.findAll()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuleResponse> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.moduleMapper.moduleToResponse(
                        this.moduleService.findById(id)
                )
        );
    }

    @GetMapping("/by-folder/{folderId}")
    public ResponseEntity<ModuleListResponse> findByFolderId(@PathVariable Long folderId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        this.moduleMapper.moduleListToResponse(
                                this.moduleFolderService.findModulesByFolderId(folderId)
                        )
                );
    }

    @PostMapping
    public ResponseEntity<ModuleResponse> create(@RequestBody ModuleDto moduleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.moduleMapper.moduleToResponse(
                        this.moduleService.create(
                                this.moduleMapper.requestToModule(moduleDto)
                        )
                )
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ModuleResponse> update(@PathVariable Long id, @RequestBody ModuleDto moduleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.moduleMapper.moduleToResponse(
                        this.moduleService.update(
                                this.moduleMapper.requestToModule(moduleDto, id)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ModuleResponse> delete(@PathVariable Long id) {
        this.moduleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{moduleId}/folder/{folderId}")
    public ResponseEntity<Void> addToFolder(@PathVariable Long moduleId, @PathVariable Long folderId) {
        this.moduleFolderService.addModuleToFolder(moduleId, folderId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{moduleId}/folder/{folderId}")
    public ResponseEntity<Void> removeFromFolder(@PathVariable Long moduleId, @PathVariable Long folderId) {
        this.moduleFolderService.removeModuleFromFolder(moduleId, folderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{moduleId}/import")
    public ResponseEntity<Void> importModulesFromFile(@PathVariable Long moduleId, @RequestParam MultipartFile file) {
        this.fileProcessorService.readFile(file, moduleId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
