package com.zup.zupProgress.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProjectDTO {
    private Long id_project;
    @NotBlank
    private String name;
    private String description;
    private String trainingInstitution;
    private LocalDate startDate;
    private LocalDate dateEnd;

}
