package com.javaseekersback.service.mappers;

import com.javaseekersback.api.model.dto.Artifact;
import com.javaseekersback.service.ArtifactTypeMatcher;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ArtifactMapper {

    private final ArtifactTypeMatcher artifactTypeMatcher;

    public ArtifactMapper(ArtifactTypeMatcher typeMatcher) {
        this.artifactTypeMatcher = typeMatcher;
    }

    public Optional<Artifact> mapArtifactByPath(String path) {
        List<String> parts = Arrays.stream(path.split("/"))
                .map(StringUtils::trim)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());
        if (!parts.isEmpty()) {
            return Optional.of(
                    Artifact.builder()
                            .name(parts.get(parts.size() - 1))
                            .path(path)
                            .type(artifactTypeMatcher.matchByPath(path))
                            .build());
        }
        return Optional.empty();
    }
}