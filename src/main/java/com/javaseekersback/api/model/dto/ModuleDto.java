package com.javaseekersback.api.model.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"moduleId", "moduleName"})
public class ModuleDto {
    private Integer moduleId;
    private String moduleName;
}
