package com.javaseekersback.javaseekersback.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ControlResponse {
    private String client;

    private Integer module;

    private List<String> controlFiles;
}
