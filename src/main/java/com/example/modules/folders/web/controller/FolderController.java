package com.example.modules.folders.web.controller;

import com.example.modules.folders.service.FolderService;
import com.example.modules.folders.web.dto.FolderDto;
import com.example.modules.folders.web.mapper.FolderMapper;
import com.example.modules.folders.web.response.FolderListResponse;
import com.example.modules.folders.web.response.FolderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/folder")
@RequiredArgsConstructor
public class FolderController {

    private final FolderService folderService;
    private final FolderMapper folderMapper;

    @GetMapping
    public ResponseEntity<FolderListResponse> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.folderMapper.folderListToResponse(
                        this.folderService.findAll()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<FolderResponse> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.folderMapper.folderToResponse(
                        this.folderService.findById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<FolderResponse> create(@RequestBody FolderDto folderDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.folderMapper.folderToResponse(
                        this.folderService.create(
                                this.folderMapper.requestToFolder(folderDto)
                        )
                )
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FolderResponse> update(@PathVariable Long id, @RequestBody FolderDto folderDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.folderMapper.folderToResponse(
                        this.folderService.update(
                                this.folderMapper.requestToFolder(folderDto, id)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FolderResponse> delete(@PathVariable Long id) {
        this.folderService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
