package com.javaseekersback.api.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ControlFileDto {
    private String client;
    private String name;
    private Integer moduleId;
}
