package com.javaseekersback.service.checks;

import com.javaseekersback.api.model.dto.Artifact;
import com.javaseekersback.api.model.response.ArtifactCheckResponse;
import com.javaseekersback.service.checks.enums.CheckResultStatus;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ControlCheckSuite {

    private final ControlImportOrderCheck controlImportOrderCheck;
    private final ControlArtifactDuplicateCheck controlDuplicateArtifact;

    public ControlCheckSuite(ControlImportOrderCheck controlImportOrderCheck, ControlArtifactDuplicateCheck controlDuplicateArtifact) {
        this.controlImportOrderCheck = controlImportOrderCheck;
        this.controlDuplicateArtifact = controlDuplicateArtifact;
    }

    public List<ArtifactCheckResponse> getErrors(List<Artifact> artifacts) {
        List<ArtifactCheckResponse> results = new ArrayList<>();

        for (Artifact artifact : artifacts) {
            List<ArtifactCheckResult> artifactCheckResults = new ArrayList<>();

            artifactCheckResults.add(controlImportOrderCheck.check(artifact, artifacts));
            artifactCheckResults.add(controlDuplicateArtifact.check(artifact, artifacts));

            List<ArtifactCheckResult> artifactErrorCheckResults = artifactCheckResults.stream()
                    .filter(r -> CheckResultStatus.ERROR.equals(r.getCheckStatus()))
                    .collect(Collectors.toList());

            ArtifactCheckResponse artifactErrorResponse = ArtifactCheckResponse.builder()
                    .name(artifact.getName())
                    .type(artifact.getType())
                    .path(artifact.getPath())
                    .artifactErrorChecks(artifactErrorCheckResults).build();
            results.add(artifactErrorResponse);
        }
        return results;
    }
}