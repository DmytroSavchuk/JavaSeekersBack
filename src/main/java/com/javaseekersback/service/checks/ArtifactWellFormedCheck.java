package com.javaseekersback.service.checks;

import com.javaseekersback.service.checks.enums.ArtifactCheckCode;
import com.javaseekersback.service.checks.enums.CheckResultStatus;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ArtifactWellFormedCheck extends ArtifactBaseCheck {

    private final ArtifactWellFormedJsonCheck jsonCheck;
    private final ArtifactWellFormedXmlCheck xmlCheck;

    public ArtifactWellFormedCheck(ArtifactWellFormedJsonCheck jsonCheck, ArtifactWellFormedXmlCheck xmlCheck) {
        this.jsonCheck = jsonCheck;
        this.xmlCheck = xmlCheck;
    }

    @Override
    public ArtifactCheckResult check(String artifactPath) {
        log.debug("check artifactPath={}", artifactPath);

        if (isApplicable(artifactPath)) {
            if (jsonCheck.isApplicable(artifactPath)) {
                return jsonCheck.check(artifactPath);
            }
            if (xmlCheck.isApplicable(artifactPath)) {
                return xmlCheck.check(artifactPath);
            }
        }

        ArtifactCheckResult.ArtifactCheckResultBuilder result = ArtifactCheckResult.builder();
        result.checkCode(ArtifactCheckCode.FILE_WELL_FORMED);
        result.actualResultMessage("File is not handled");
        result.checkStatus(CheckResultStatus.SKIPPED);
        return result.build();
    }
}