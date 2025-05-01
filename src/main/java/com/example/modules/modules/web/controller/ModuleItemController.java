package com.example.modules.modules.web.controller;

import com.example.modules.modules.service.ModuleItemService;
import com.example.modules.modules.web.dto.ModuleItemDto;
import com.example.modules.modules.web.mapper.ModuleItemMapper;
import com.example.modules.modules.web.response.ModuleItemListResponse;
import com.example.modules.modules.web.response.ModuleItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/module-item")
@RequiredArgsConstructor
public class ModuleItemController {

    private final ModuleItemService moduleItemService;
    private final ModuleItemMapper moduleItemMapper;

    @GetMapping("/by-module/{id}")
    public ResponseEntity<ModuleItemListResponse> findModuleItemsByModuleId(@PathVariable("id") Long moduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.moduleItemMapper.moduleItemListToListResponse(
                        this.moduleItemService.findModuleItemsByModuleId(moduleId)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuleItemResponse> findModuleItemById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.moduleItemMapper.moduleItemToResponse(
                        this.moduleItemService.findModuleItemById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<ModuleItemResponse> addItemToModule(@RequestBody ModuleItemDto moduleItemDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.moduleItemMapper.moduleItemToResponse(
                        this.moduleItemService.addItemToModule(
                                this.moduleItemMapper.requestToModuleItem(moduleItemDto)
                        )
                )
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ModuleItemResponse> updateItemToModule(@PathVariable Long id, @RequestBody ModuleItemDto moduleItemDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.moduleItemMapper.moduleItemToResponse(
                        this.moduleItemService.updateItemToModule(
                                this.moduleItemMapper.requestToModuleItem(moduleItemDto, id)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItemFromModule(@PathVariable Long id) {
        this.moduleItemService.removeItemFromModule(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
