package com.javaseekersback.service.checks;

import com.javaseekersback.api.model.dto.Artifact;

import java.util.List;

public interface ControlCheck {

    ArtifactCheckResult check(Artifact checkArtifact, List<Artifact> controlArtifacts);
}
