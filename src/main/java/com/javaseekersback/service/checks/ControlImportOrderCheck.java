package com.javaseekersback.service.checks;

import com.javaseekersback.api.model.dto.Artifact;
import com.javaseekersback.api.model.enums.ArtifactType;
import com.javaseekersback.service.checks.enums.ArtifactCheckCode;
import com.javaseekersback.service.checks.enums.CheckResultStatus;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

@Component
public class ControlImportOrderCheck implements ControlCheck {

    private static final String SUCCESS_RESULT = "Correct import order";

    @Override
    public ArtifactCheckResult check(@NotNull Artifact checkArtifact, @NotNull List<Artifact> controlArtifacts) {
        if (controlArtifacts.isEmpty()) {
            return ArtifactCheckResult.builder()
                    .checkStatus(CheckResultStatus.SKIPPED)
                    .checkCode(ArtifactCheckCode.IMPORT_ORDER)
                    .build();
        }
        List<Artifact> artifactsImportedAfter = controlArtifacts.subList(controlArtifacts.indexOf(checkArtifact) + 1, controlArtifacts.size());
        List<Artifact> artifactsDependent = artifactsImportedAfter.stream()
                .filter(a -> checkArtifact.getType().getDependencies().contains(a.getType()))
                .collect(Collectors.toList());

        if (!artifactsDependent.isEmpty()) {
            String errorDetails = checkArtifact.getType().getDependencies()
                    .stream()
                    .map(ArtifactType::getTypeName)
                    .collect(Collectors.joining(", "));
            return ArtifactCheckResult.builder()
                    .checkStatus(CheckResultStatus.ERROR)
                    .checkCode(ArtifactCheckCode.IMPORT_ORDER)
                    .actualResultMessage("Must be imported after next groups: " + errorDetails)
                    .build();
        }
        return ArtifactCheckResult.builder()
                .checkStatus(CheckResultStatus.SUCCESS)
                .checkCode(ArtifactCheckCode.IMPORT_ORDER)
                .actualResultMessage(SUCCESS_RESULT)
                .build();
    }
}