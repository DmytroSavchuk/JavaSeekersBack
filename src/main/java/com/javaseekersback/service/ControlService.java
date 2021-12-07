package com.javaseekersback.service;

import com.javaseekersback.api.model.request.ControlRequest;
import com.javaseekersback.api.model.response.ControlResponse;

public interface ControlService {
    ControlResponse getAllControls(ControlRequest controlRequest);
}
