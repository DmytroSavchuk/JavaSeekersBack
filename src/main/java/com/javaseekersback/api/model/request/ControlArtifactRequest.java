package com.javaseekersback.api.model.request;

import org.apache.commons.lang3.StringUtils;

import java.nio.file.FileSystems;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ControlArtifactRequest {

    private String client;

    private Integer module;

    private String controlName;

    public String toString() {
        return Stream.of(client, module, controlName)
                .filter(Objects::nonNull)
                .map(String::valueOf)
                .collect(Collectors.joining(FileSystems.getDefault().getSeparator()));
    }

    public String asPath() {
        String modulePath = Objects.isNull(module) ? "" : "modules/" + module;
        return Stream.of(client, modulePath, controlName)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining("/"));
    }
}