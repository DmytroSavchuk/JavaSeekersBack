package com.javaseekersback.api.model.response;

import com.javaseekersback.api.model.dto.ModuleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleResponse {
    private Set<ModuleDto> modules;
}
