package com.javaseekersback.service;

import com.javaseekersback.api.model.request.ControlArtifactRequest;
import com.javaseekersback.api.model.response.ControlArtifactsResponse;

public interface ArtifactsService {

    ControlArtifactsResponse get(ControlArtifactRequest request);

}