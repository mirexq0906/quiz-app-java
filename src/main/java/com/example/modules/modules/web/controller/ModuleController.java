package com.example.modules.modules.web.controller;

import com.example.modules.modules.service.ModuleService;
import com.example.modules.modules.web.dto.ModuleDto;
import com.example.modules.modules.web.mapper.ModuleMapper;
import com.example.modules.modules.web.response.ModuleListResponse;
import com.example.modules.modules.web.response.ModuleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/module")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;
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

}
