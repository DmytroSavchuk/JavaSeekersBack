package com.javaseekersback.javaseekersback.api.model.dto;

import com.javaseekersback.javaseekersback.api.model.enums.ArtifactType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artifact {

    private ArtifactType type;

    private String name;
}
