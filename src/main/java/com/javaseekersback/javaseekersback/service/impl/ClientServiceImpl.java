package com.javaseekersback.javaseekersback.service.impl;

import com.javaseekersback.javaseekersback.api.model.response.ClientResponse;
import com.javaseekersback.javaseekersback.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.javaseekersback.javaseekersback.service.constants.PathConstants.CONFIGURER_ABSOLUTE_PATH;
import static com.javaseekersback.javaseekersback.service.constants.PathConstants.CONFIGURER_SCHEMA_PATH;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    @Override
    public ClientResponse getAllClients() {
        try (Stream<Path> paths = Files.walk(Path.of(CONFIGURER_ABSOLUTE_PATH, CONFIGURER_SCHEMA_PATH), 1)) {
            return new ClientResponse(paths.filter(Files::isDirectory)
                    .filter(c -> !c.endsWith(CONFIGURER_SCHEMA_PATH))
                    .map(c -> c.getFileName().toString())
                    .collect(Collectors.toSet()));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Problem while accessing ");
        }
    }
}
