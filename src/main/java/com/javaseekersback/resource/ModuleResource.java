package com.javaseekersback.resource;

import com.javaseekersback.api.model.response.ModuleResponse;
import com.javaseekersback.service.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("modules")
public class ModuleResource {
    private final ModuleService moduleService;

    @Autowired
    public ModuleResource(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping
    @Valid
    @Operation(summary = "Get list of all AA modules")
    public ResponseEntity<ModuleResponse> getAllModules() {
        log.info("GET modules/");

        return ResponseEntity.ok(moduleService.getAllModules());
    }
}
