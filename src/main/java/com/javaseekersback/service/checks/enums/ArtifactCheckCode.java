package com.javaseekersback.service.checks.enums;

import lombok.Getter;

@Getter
public enum ArtifactCheckCode {
    PRESENT_IN_FILE_SYSTEM_CHECK("Present in file System");

    private String checkName;

    ArtifactCheckCode(String checkName) {
        this.checkName = checkName;
    }
}