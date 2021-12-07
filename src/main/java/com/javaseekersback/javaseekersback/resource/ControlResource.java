package com.javaseekersback.javaseekersback.resource;

import com.javaseekersback.javaseekersback.api.model.request.ControlRequest;
import com.javaseekersback.javaseekersback.api.model.response.ControlResponse;
import com.javaseekersback.javaseekersback.service.impl.ControlServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("controls")
public class ControlResource {

    private final ControlServiceImpl controlService;

    @Autowired
    public ControlResource(ControlServiceImpl controlService) {
        this.controlService = controlService;
    }

    @GetMapping
    @Valid
    @Operation(summary = "Get list of all project control files")
    public ResponseEntity<ControlResponse> getAllControls(ControlRequest controlRequest) {
        log.info("getAllControls controlRequest{}", controlRequest);

        return ResponseEntity.ok(controlService.getAllControls());
    }
}
