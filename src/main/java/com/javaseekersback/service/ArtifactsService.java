package com.javaseekersback.service;

import com.javaseekersback.api.model.request.ControlArtifactRequest;
import com.javaseekersback.api.model.response.ArtifactCheckResponse;
import com.javaseekersback.api.model.response.ControlArtifactsChecksResponse;
import com.javaseekersback.api.model.response.ControlArtifactsResponse;

import java.util.List;

public interface ArtifactsService {

    ControlArtifactsResponse get(ControlArtifactRequest request);

    ControlArtifactsChecksResponse getWithErrors(ControlArtifactRequest request);
}