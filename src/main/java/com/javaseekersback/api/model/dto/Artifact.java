package com.javaseekersback.api.model.dto;

import com.javaseekersback.api.model.enums.ArtifactType;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Artifact artifact = (Artifact) o;
        return path.equals(artifact.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }
}