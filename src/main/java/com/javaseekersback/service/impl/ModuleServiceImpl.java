package com.javaseekersback.service.impl;

import com.javaseekersback.api.model.dto.ModuleDto;
import com.javaseekersback.api.model.enums.Module;
import com.javaseekersback.api.model.response.ModuleResponse;
import com.javaseekersback.service.ModuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ModuleServiceImpl implements ModuleService {
    @Override
    public ModuleResponse getAllModules() {
        return new ModuleResponse(Arrays.stream(Module.values())
                .map(m -> new ModuleDto(m.getId(), m.getName()))
                .collect(Collectors.toSet()));
    }
}
