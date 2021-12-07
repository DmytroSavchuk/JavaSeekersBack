package com.javaseekersback.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ControlRequest {

    private String client;

    private Integer module;

    private String controlNameFilter;
}
