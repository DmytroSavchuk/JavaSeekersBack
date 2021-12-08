package com.javaseekersback.api.model.response;

import com.javaseekersback.api.model.enums.ArtifactType;
import com.javaseekersback.service.checks.ArtifactCheckResult;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtifactCheckResponse {
    private ArtifactType type;

    private String name;

    private String path;

    private List<ArtifactCheckResult> artifactErrorChecks;
}
