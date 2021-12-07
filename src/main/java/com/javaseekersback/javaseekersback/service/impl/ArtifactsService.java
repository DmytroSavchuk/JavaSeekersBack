package com.javaseekersback.javaseekersback.service.impl;

import com.javaseekersback.javaseekersback.api.model.request.ControlArtifactRequest;
import com.javaseekersback.javaseekersback.api.model.response.ControlArtifactsResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArtifactsService {

    public ControlArtifactsResponse get(ControlArtifactRequest request) {
        log.info("get request={}", request);
        return new ControlArtifactsResponse();
    }
}
