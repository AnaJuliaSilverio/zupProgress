package com.zup.zupProgress.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "project")
@Entity()
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_project;
    @NotBlank
    private String name;
    private String description;
    private String nameInstructor;
    private String emailInstructor;
    private String nameLeadership;
    private String emailLeadership;
    private String trainingInstitution;
    private LocalDate startDate;
    private LocalDate dateEnd;

}
