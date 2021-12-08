package com.javaseekersback.api.model.response;

import com.javaseekersback.api.model.dto.ControlFileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControlResponse {
    private Set<ControlFileDto> controlFiles;
}
