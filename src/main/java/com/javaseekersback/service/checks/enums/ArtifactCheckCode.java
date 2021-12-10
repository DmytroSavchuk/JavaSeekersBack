package com.javaseekersback.service.checks.enums;

import lombok.Getter;

@Getter
public enum ArtifactCheckCode {
    PRESENT_IN_FILE_SYSTEM_CHECK("Present in file System"),
    FILE_WELL_FORMED("File well formed"),
    IMPORT_ORDER("Artifact imported in correct order"),
    DUPLICATE_IMPORT("Artifact Imported once");

    private final String checkName;

    ArtifactCheckCode(String checkName) {
        this.checkName = checkName;
    }
}