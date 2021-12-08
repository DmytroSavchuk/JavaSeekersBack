package com.javaseekersback.api.model.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ControlArtifactsChecksResponse {

    private String client;

    private Integer module;

    private String controlName;

    private List<ArtifactCheckResponse> artifactErrorChecks;
}
