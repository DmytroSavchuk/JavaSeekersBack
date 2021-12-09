package com.javaseekersback.service.checks;

import com.javaseekersback.api.model.dto.Artifact;
import com.javaseekersback.api.model.enums.ArtifactType;
import com.javaseekersback.api.model.response.ArtifactCheckResponse;
import com.javaseekersback.service.checks.enums.ArtifactCheckCode;
import com.javaseekersback.service.checks.enums.CheckResultStatus;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ControlCheckSuite {

    public List<ArtifactCheckResponse> getErrors(List<Artifact> artifacts) {
        //TODO: think to create separate check
        List<ArtifactCheckResponse> results = new ArrayList<>();

        for (Artifact artifact : artifacts) {
            List<Artifact> artifactsImportedAfter = artifacts.subList(artifacts.indexOf(artifact) + 1, artifacts.size());
            List<Artifact> artifactsDependent = artifactsImportedAfter.stream()
                    .filter(a -> artifact.getType().getDependencies().contains(a.getType()))
                    .collect(Collectors.toList());

            if (!artifactsDependent.isEmpty()) {
                String errorDetails = artifact.getType().getDependencies()
                        .stream()
                        .map(ArtifactType::getTypeName)
                        .collect(Collectors.joining(", "));
                ArtifactCheckResponse artifactErrorResponse = ArtifactCheckResponse.builder()
                        .name(artifact.getName())
                        .type(artifact.getType())
                        .path(artifact.getPath())
                        .artifactErrorChecks(List.of(ArtifactCheckResult.builder()
                                .checkStatus(CheckResultStatus.ERROR)
                                .checkCode(ArtifactCheckCode.IMPORT_ORDER)
                                .actualResultMessage("Must be imported after: " + errorDetails)
                                .build())).build();
                results.add(artifactErrorResponse);
            }
        }
        return results;
    }
}