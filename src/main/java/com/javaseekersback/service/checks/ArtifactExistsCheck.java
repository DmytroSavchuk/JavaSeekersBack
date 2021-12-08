package com.javaseekersback.service.checks;

import com.javaseekersback.service.checks.enums.ArtifactCheckCode;
import com.javaseekersback.service.checks.enums.CheckResultStatus;

import org.springframework.stereotype.Component;

import java.nio.file.Files;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ArtifactExistsCheck extends ArtifactBaseCheck {

    private static final String SUCCESS_RESULT = "Artifact file exists";
    private static final String ERROR_RESULT = "Artifact file doesn't exist";

    @Override
    public ArtifactCheckResult check(String artifactPath) {
        log.debug("check artifactPath={}", artifactPath);

        ArtifactCheckResult.ArtifactCheckResultBuilder result = ArtifactCheckResult.builder();

        result.checkCode(ArtifactCheckCode.PRESENT_IN_FILE_SYSTEM_CHECK);

        if (Files.exists(getFullPath(artifactPath))) {
            result.actualResultMessage(SUCCESS_RESULT);
            result.checkStatus(CheckResultStatus.SUCCESS);
        } else {
            result.actualResultMessage(ERROR_RESULT);
            result.checkStatus(CheckResultStatus.ERROR);
        }
        return result.build();
    }
}
