package com.javaseekersback.api.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public enum ArtifactType {
    DASHBOARD("dashboards", "Dashboard"),
    DECISION_TREE("decisionTrees", "Decision Tree"),
    FOREIGN_KEY("foreignkey", "Foreign Key"),
    FUNCTION("function", "Function"),
    GROOVY("groovy", "Groovy Script"),
    IMPORT_CONFIG("importConfigs", "Import Config"),
    JAVA_SCRIPT("javascript", "Java Script"),
    JOB("jobs", "Job"),
    LAYER("Layers", "Layer"),
    MAP("Maps", "Map"),
    MENU("menus", "Menu"),
    M_VIEW("mview", "Materialized View"),
    MODEL("models", "Model"),
    PROCEDURE("procedure", "Procedure"),
    POPUP_WINDOW("popups", "Popup Window"),
    REPORT("report", "Report"),
    SECURITY_ROLE("securityRoles", "Security Role"),
    SEQUENCE("sequence", "Sequence"),
    SETUP_DATA("setupData", "Setup Data"),
    TABLE("table", "Table"),
    TREATMENTS("treatments", "Treatment"),
    TYPE("type", "Type"),
    TRIGGER("trigger", "Trigger"),
    VIEW("view", "View"),
    WINDOW("windows", "Window"),
    UNDEFINED("", "Undefined");

    private final String type;
    private final String typeName;

    private List<ArtifactType> dependencies = new ArrayList<>();

    static {
        MENU.dependencies = List.of(GROOVY, JAVA_SCRIPT, WINDOW);
        POPUP_WINDOW.dependencies = List.of(GROOVY, JAVA_SCRIPT, SETUP_DATA);
        SECURITY_ROLE.dependencies = List.of(WINDOW);
        SETUP_DATA.dependencies = List.of(TABLE);
        TABLE.dependencies = List.of(FOREIGN_KEY);
        WINDOW.dependencies = List.of(GROOVY, JAVA_SCRIPT, SETUP_DATA);
    }

    ArtifactType(String type, String name) {
        this.type = type;
        this.typeName = name;
    }

    public String getType() {
        return type;
    }

    @JsonValue
    public String getTypeName() {
        return this.typeName;
    }

    public List<ArtifactType> getDependencies() {
        return dependencies;
    }

    public static Stream<ArtifactType> stream() {
        return Stream.of(ArtifactType.values());
    }
}