package com.javaseekersback.service.checks;

import com.javaseekersback.service.constants.PathConstants;

import java.nio.file.Path;

public abstract class ArtifactBaseCheck implements ArtifactCheck {

    public abstract ArtifactCheckResult check(String artifactPath);

    public Path getFullPath(String artifactPath) {
        return Path.of(PathConstants.CONFIGURER_ABSOLUTE_PATH, PathConstants.CONFIGURER_SCHEMA_PATH, artifactPath);
    }
}
