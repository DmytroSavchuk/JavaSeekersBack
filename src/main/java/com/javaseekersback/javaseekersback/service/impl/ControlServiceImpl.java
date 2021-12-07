package com.javaseekersback.javaseekersback.service.impl;

import com.javaseekersback.javaseekersback.api.model.response.ControlResponse;
import com.javaseekersback.javaseekersback.service.ControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ControlServiceImpl implements ControlService {
    @Override
    public ControlResponse getAllControls() {
        return new ControlResponse("AMS_NYC", 17, List.of());
    }
}
