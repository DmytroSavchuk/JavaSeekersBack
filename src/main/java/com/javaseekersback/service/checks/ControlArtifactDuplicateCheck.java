package com.javaseekersback.service.checks;

import com.javaseekersback.api.model.dto.Artifact;
import com.javaseekersback.service.checks.enums.ArtifactCheckCode;
import com.javaseekersback.service.checks.enums.CheckResultStatus;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ControlArtifactDuplicateCheck implements ControlCheck {

    private static final String SUCCESS_RESULT = "Artifact Imported once.";
    private static final String ERROR_RESULT = "Imported more than one time: ";

    @Override
    public ArtifactCheckResult check(Artifact checkArtifact, List<Artifact> controlArtifacts) {
        if (controlArtifacts.isEmpty()) {
            return ArtifactCheckResult.builder()
                    .checkStatus(CheckResultStatus.SKIPPED)
                    .checkCode(ArtifactCheckCode.DUPLICATE_IMPORT)
                    .build();
        }
        long countImports = controlArtifacts.stream()
                .filter(checkArtifact::equals)
                .count();
        //also can check in other controls
        if (countImports > 1) {
            return ArtifactCheckResult.builder()
                    .checkStatus(CheckResultStatus.ERROR)
                    .checkCode(ArtifactCheckCode.DUPLICATE_IMPORT)
                    .actualResultMessage(ERROR_RESULT + countImports + " time(s)")
                    .build();

        }
        return ArtifactCheckResult.builder()
                .checkStatus(CheckResultStatus.SUCCESS)
                .checkCode(ArtifactCheckCode.DUPLICATE_IMPORT)
                .actualResultMessage(SUCCESS_RESULT)
                .build();
    }
}