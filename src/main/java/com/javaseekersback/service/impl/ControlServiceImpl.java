package com.javaseekersback.service.impl;

import com.javaseekersback.api.model.request.ControlRequest;
import com.javaseekersback.api.model.response.ControlResponse;
import com.javaseekersback.service.ControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class ControlServiceImpl implements ControlService {
    @Override
    public ControlResponse getAllControls(ControlRequest controlRequest) {
        return ControlResponse.builder()
                .client("AMS_NYC")
                .module(17)
                .controlFiles(Collections.emptyList())
                .build();
    }
}
