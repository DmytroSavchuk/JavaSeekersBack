package com.javaseekersback.service.impl;

import com.javaseekersback.api.model.dto.Artifact;
import com.javaseekersback.api.model.request.ControlArtifactRequest;
import com.javaseekersback.api.model.response.ArtifactCheckResponse;
import com.javaseekersback.api.model.response.ControlArtifactsChecksResponse;
import com.javaseekersback.api.model.response.ControlArtifactsResponse;
import com.javaseekersback.service.ArtifactsService;
import com.javaseekersback.service.checks.ArtifactCheckSuite;
import com.javaseekersback.service.constants.PathConstants;
import com.javaseekersback.service.mappers.ArtifactMapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArtifactsServiceImpl implements ArtifactsService {
    private final ArtifactMapper artifactMapper;
    private final ArtifactCheckSuite artifactCheckSuite;

    @Autowired
    public ArtifactsServiceImpl(ArtifactMapper artifactMapper, ArtifactCheckSuite artifactCheckSuite) {
        this.artifactMapper = artifactMapper;
        this.artifactCheckSuite = artifactCheckSuite;
    }

    @Override
    public ControlArtifactsResponse get(ControlArtifactRequest request) {
        log.info("get request={}", request);

        return ControlArtifactsResponse.builder()
                .client(request.getClient())
                .module(request.getModule())
                .controlName(request.getControlName())
                .artifactList(getArtifacts(request.asPath()))
                .build();
    }

    private List<Artifact> getArtifacts(@NotNull String controlFilePath) {
        log.info("getArtifacts controlFilePath={}", controlFilePath);

        Path path = Path.of(PathConstants.CONFIGURER_ABSOLUTE_PATH, PathConstants.CONFIGURER_SCHEMA_PATH, controlFilePath);

        if (Files.isReadable(path)) {
            try {
                return Files.readAllLines(path, StandardCharsets.UTF_8)
                        .stream()
                        .map(StringUtils::trim)
                        .filter(line -> !StringUtils.startsWith(line, "#"))
                        .map(artifactMapper::mapArtifactByPath)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                log.error("Exception while file reading", e);
            }
        }
        return Collections.emptyList();
    }

    @Override
    public ControlArtifactsChecksResponse getWithErrors(ControlArtifactRequest request) {
        List<Artifact> artifacts = getArtifacts(request.asPath());
        List<ArtifactCheckResponse> checksList = artifacts.stream()
                .map(a -> ArtifactCheckResponse.builder()
                        .type(a.getType())
                        .name(a.getName())
                        .path(a.getPath())
                        .artifactErrorChecks(artifactCheckSuite.getErrors(Path.of(request.getClient(), a.getPath()).toString()))
                        .build())
                .collect(Collectors.toList());

        return ControlArtifactsChecksResponse.builder()
                .client(request.getClient())
                .module(request.getModule())
                .controlName(request.getControlName())
                .artifactErrorChecks(checksList)
                .build();
    }
}