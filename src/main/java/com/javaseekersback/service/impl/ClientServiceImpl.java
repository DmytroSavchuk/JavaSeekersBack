package com.javaseekersback.service.impl;

import com.javaseekersback.api.model.response.ClientResponse;
import com.javaseekersback.service.ClientService;
import com.javaseekersback.service.constants.PathConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    @Override
    public ClientResponse getAllClients() {
        try (Stream<Path> paths = Files.walk(
                Path.of(PathConstants.CONFIGURER_ABSOLUTE_PATH, PathConstants.CONFIGURER_SCHEMA_PATH), 1)) {
            return new ClientResponse(paths.filter(Files::isDirectory)
                    .filter(c -> !c.endsWith(PathConstants.CONFIGURER_SCHEMA_PATH))
                    .map(c -> c.getFileName().toString())
                    .collect(Collectors.toSet()));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Problem while accessing ");
        }
    }
}
