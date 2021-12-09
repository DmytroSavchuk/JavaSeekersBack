package com.javaseekersback.service.checks;

import com.javaseekersback.service.checks.enums.ArtifactCheckCode;
import com.javaseekersback.service.checks.enums.CheckResultStatus;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Component;

import java.nio.file.Files;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ArtifactWellFormedJsonCheck extends ArtifactBaseCheck {

    private static final String SUCCESS_RESULT = "File is well formed";
    private static final String ERROR_RESULT = "Malformed JSON";

    @Override
    public ArtifactCheckResult check(String artifactPath) {
        log.debug("check artifactPath={}", artifactPath);
        ArtifactCheckResult.ArtifactCheckResultBuilder result = ArtifactCheckResult.builder();

        result.checkCode(ArtifactCheckCode.FILE_WELL_FORMED);

        if (isApplicable(artifactPath)) {
            try {
                JSONParser pa = new JSONParser(Files.readString(getFullPath(artifactPath)));
                pa.parse();
                result.actualResultMessage(SUCCESS_RESULT);
                result.checkStatus(CheckResultStatus.SUCCESS);
            } catch (ParseException pae) {
                result.actualResultMessage(ERROR_RESULT);
                result.checkStatus(CheckResultStatus.ERROR);
            } catch (Exception pae) {
                result.actualResultMessage("Can't check json");
                result.checkStatus(CheckResultStatus.SKIPPED);
            }
        } else {
            result.actualResultMessage("File is not .json");
            result.checkStatus(CheckResultStatus.SKIPPED);
        }
        return result.build();
    }

    @Override
    public boolean isApplicable(String artifactPath) {
        return super.isApplicable(artifactPath) && artifactPath.contains(".json");
    }
}
