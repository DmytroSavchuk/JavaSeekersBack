package com.javaseekersback.service.checks.enums;

import lombok.Getter;

@Getter
public enum ArtifactCheckCode {
    PRESENT_IN_FILE_SYSTEM_CHECK("Present in file System"),
    FILE_WELL_FORMED("File well formed");

    private final String checkName;

    ArtifactCheckCode(String checkName) {
        this.checkName = checkName;
    }
}