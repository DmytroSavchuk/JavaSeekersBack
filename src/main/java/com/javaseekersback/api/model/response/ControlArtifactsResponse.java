package com.javaseekersback.api.model.response;

import com.javaseekersback.api.model.dto.Artifact;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ControlArtifactsResponse {

    private String controlName;

    private String client;

    private Integer module;

    private List<Artifact> artifactList;
}
