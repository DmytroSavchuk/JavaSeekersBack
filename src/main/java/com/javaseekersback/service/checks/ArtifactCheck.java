package com.javaseekersback.service.checks;

public interface ArtifactCheck {

    ArtifactCheckResult check(String artifactPath);
}