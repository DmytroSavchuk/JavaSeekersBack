package com.javaseekersback.service.checks;

import com.javaseekersback.service.checks.enums.ArtifactCheckCode;
import com.javaseekersback.service.checks.enums.CheckResultStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ArtifactCheckResult {

    private ArtifactCheckCode checkCode;

    private CheckResultStatus checkStatus;

    private String actualResultMessage;
}
