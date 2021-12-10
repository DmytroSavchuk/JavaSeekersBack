package com.javaseekersback.service.impl;

import com.javaseekersback.api.model.dto.ControlFileDto;
import com.javaseekersback.api.model.request.ControlRequest;
import com.javaseekersback.api.model.response.ControlResponse;
import com.javaseekersback.service.ControlService;
import com.javaseekersback.service.constants.PathConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class ControlServiceImpl implements ControlService {
    /**
     * Max depth for looking for the control files. Counting starts from schema folder. E.g. schema - 0,
     * agile_standard - 1, modules - 2, 17 - 3, ControlFile.lst - 4
     */
    private static final Integer MAX_CONTROL_FILE_DEPTH = 4;
    private static final String CONTROL_FILE_EXTENSION = ".lst";

    @Override
    public ControlResponse getAllControls(ControlRequest controlRequest) {
        return new ControlResponse(
                filterControls(findAllControls(), controlRequest)
        );
    }

    private Set<ControlFileDto> findAllControls() {
        try (Stream<Path> paths = Files.walk(Path.of(PathConstants.CONFIGURER_ABSOLUTE_PATH,
                PathConstants.CONFIGURER_SCHEMA_PATH), MAX_CONTROL_FILE_DEPTH)) {
            return paths.filter(Files::isRegularFile)
                    .filter(c -> c.toString().endsWith(CONTROL_FILE_EXTENSION))
                    .map(this::pathToControlDto)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Problem while accessing Control Files");
        }
    }

    private Set<ControlFileDto> filterControls(Set<ControlFileDto> controlFiles, ControlRequest controlRequest) {
        return controlFiles.stream()
                .filter(c -> controlRequest.getClient() == null || controlRequest.getClient().isEmpty()
                        || c.getClient().equalsIgnoreCase(controlRequest.getClient()))
                .filter(c -> controlRequest.getModule() == null
                        || Objects.equals(c.getModuleId(), controlRequest.getModule()))
                .filter(c -> controlRequest.getControlNameFilter() == null || controlRequest.getControlNameFilter().isEmpty()
                        || c.getName().toUpperCase().contains(controlRequest.getControlNameFilter().toUpperCase()))
                .collect(Collectors.toSet());
    }

    private ControlFileDto pathToControlDto(Path controlPath) {
        String client = null;
        Integer moduleId = null;
        var controlName = new StringBuilder();
        Iterator<Path> partsIterator = controlPath.iterator();

        while (partsIterator.hasNext()) {
            if (partsIterator.next().toString().equals("schema")) {
                client = partsIterator.next().toString();

                var moduleNamePart = partsIterator.next().toString();
                controlName.append(moduleNamePart);

                if (moduleNamePart.equals("modules")) {
                    moduleId = Integer.parseInt(partsIterator.next().toString());

                    controlName.append("/").append(moduleId);
                }

                partsIterator.forEachRemaining(p -> controlName.append("/").append(p.toString()));
            }
        }

        return ControlFileDto.builder()
                .client(client)
                .moduleId(moduleId)
                .name(controlName.toString())
                .build();
    }
}
