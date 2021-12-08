package com.javaseekersback.service.mappers;

import com.javaseekersback.api.model.request.ControlArtifactRequest;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

@Component
public class ControlArtifactRequestMapper {

    public ControlArtifactRequest mapFromPath(@NotNull String path) {
        List<String> parts = Arrays.stream(path.split("/")).collect(Collectors.toList());
        return ControlArtifactRequest.builder()
                .client(getClient(parts))
                .module(getModule(parts))
                .controlName(getControlName(parts))
                .build();
    }

    private String getClient(List<String> parts) {
        return parts.isEmpty() ? null : parts.get(0);
    }

    private Integer getModule(List<String> parts) {
        return parts.size() == 3 ? Integer.valueOf(parts.get(1)) : null;
    }

    private String getControlName(List<String> parts) {
        return parts.size() > 1 ? parts.get(parts.size() - 1) : null;
    }
}
