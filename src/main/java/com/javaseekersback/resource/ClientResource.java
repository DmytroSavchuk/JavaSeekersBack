package com.javaseekersback.resource;

import com.javaseekersback.api.model.response.ClientResponse;
import com.javaseekersback.service.ClientService;
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
@RequestMapping("clients")
public class ClientResource {
    private final ClientService clientService;

    @Autowired
    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @Valid
    @Operation(summary = "Get list of all AA clients")
    public ResponseEntity<ClientResponse> getAllClients() {
        log.info("GET clients/");

        return ResponseEntity.ok(clientService.getAllClients());
    }
}
