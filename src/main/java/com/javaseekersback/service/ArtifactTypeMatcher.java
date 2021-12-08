package com.javaseekersback.service;

import com.javaseekersback.api.model.enums.ArtifactType;

import org.springframework.stereotype.Component;

@Component
public class ArtifactTypeMatcher {

    private final String TYPE_MATCHER_FORMAT = "(.*)%s/(.*)";

    public ArtifactType matchByPath(String artifactPath) {
        return
                ArtifactType.stream()
                        .filter(t -> artifactPath.matches(String.format(TYPE_MATCHER_FORMAT, t.getType())))
                        .findFirst()
                        .orElseGet(() -> ArtifactType.UNDEFINED);
    }
}