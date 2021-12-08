package com.javaseekersback.service.checks;

import com.javaseekersback.service.checks.enums.ArtifactCheckCode;
import com.javaseekersback.service.checks.enums.CheckResultStatus;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.file.Files;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ArtifactWellFormedXmlCheck extends ArtifactBaseCheck {

    private static final String SUCCESS_RESULT = "File is well formed";
    private static final String ERROR_RESULT = "File isn't well formed";

    @Override
    public ArtifactCheckResult check(String artifactPath) {
        log.debug("check artifactPath={}", artifactPath);
        ArtifactCheckResult.ArtifactCheckResultBuilder result = ArtifactCheckResult.builder();

        result.checkCode(ArtifactCheckCode.FILE_WELL_FORMED);

        if (isApplicable(artifactPath)) {
            try {
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                builder.parse(Files.newInputStream(getFullPath(artifactPath)));
                result.actualResultMessage(SUCCESS_RESULT);
                result.checkStatus(CheckResultStatus.SUCCESS);
            } catch (IOException | SAXException e) {
                log.error("Error parsing xml");
                result.actualResultMessage(ERROR_RESULT);
                result.checkStatus(CheckResultStatus.ERROR);
            } catch (ParserConfigurationException pae) {
                result.actualResultMessage("Can't check xml");
                result.checkStatus(CheckResultStatus.SKIPPED);
            }
        } else {
            result.actualResultMessage("File is not .xml");
            result.checkStatus(CheckResultStatus.SKIPPED);
        }
        return result.build();
    }

    @Override
    public boolean isApplicable(String artifactPath) {
        return super.isApplicable(artifactPath) && artifactPath.contains(".xml");
    }
}
