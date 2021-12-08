package com.javaseekersback.api.model.dto;

import com.javaseekersback.api.model.enums.ArtifactType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artifact {

    private ArtifactType type = ArtifactType.UNDEFINED;

    private String name;

    private String path;
}