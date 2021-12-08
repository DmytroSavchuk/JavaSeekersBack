package com.javaseekersback.service.checks;

import com.javaseekersback.service.checks.enums.CheckResultStatus;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ArtifactCheckSuite {

    private final ArtifactExistsCheck artifactPresentCheck;

    public ArtifactCheckSuite(ArtifactExistsCheck artifactPresentCheck) {
        this.artifactPresentCheck = artifactPresentCheck;
    }

    public List<ArtifactCheckResult> checkAll(String artifactPath) {
        log.debug("checkAll artifactPath={}", artifactPath);

        List<ArtifactCheckResult> testResults = new ArrayList<>();

        testResults.add(artifactPresentCheck.check(artifactPath));

        return testResults;
    }

    public List<ArtifactCheckResult> getErrors(String artifactPath) {
        log.debug ("getErrors artifactPath={}", artifactPath);
        return checkAll(artifactPath)
                .stream()
                .filter(r -> CheckResultStatus.ERROR.equals(r.getCheckStatus()))
                .collect(Collectors.toList());
    }
}
