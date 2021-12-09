package com.javaseekersback.resource;

import com.javaseekersback.api.model.request.ControlArtifactRequest;
import com.javaseekersback.api.model.response.ArtifactTypeResponse;
import com.javaseekersback.api.model.response.ControlArtifactsChecksResponse;
import com.javaseekersback.api.model.response.ControlArtifactsResponse;
import com.javaseekersback.service.impl.ArtifactsServiceImpl;
import com.javaseekersback.service.mappers.ControlArtifactRequestMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("artifacts")
public class ArtifactResource {

    private final ArtifactsServiceImpl artifactsService;
    private final ControlArtifactRequestMapper artifactRequestMapper;

    @Autowired
    public ArtifactResource(ArtifactsServiceImpl artifactsService, ControlArtifactRequestMapper artifactRequestMapper) {
        this.artifactsService = artifactsService;
        this.artifactRequestMapper = artifactRequestMapper;
    }

    @GetMapping
    @Operation(summary = "Get list of all artifacts from control file")
    public ResponseEntity<ControlArtifactsResponse> getAllArtifacts(@RequestParam String controlPath) {
        log.info("getAllArtifacts controlPath={}", controlPath);

        ControlArtifactRequest request = artifactRequestMapper.mapFromPath(controlPath);

        return ResponseEntity.ok(artifactsService.get(request));
    }

    @GetMapping("errors")
    @Operation(summary = "Get list of all artifacts from control file with error checks")
    public ResponseEntity<ControlArtifactsChecksResponse> getAllArtifactsWithErrorChecks(@RequestParam String controlPath) {
        log.info("getAllArtifactsWithErrorChecks controlPath={}", controlPath);

        ControlArtifactRequest request = artifactRequestMapper.mapFromPath(controlPath);

        return ResponseEntity.ok(artifactsService.getWithErrors(request));
    }

    @GetMapping("types")
    @Operation(summary = "Get list of all artifact types")
    public ResponseEntity<ArtifactTypeResponse> getAllArtifactTypes() {
        return ResponseEntity.ok(artifactsService.getArtifactTypes());
    }
}