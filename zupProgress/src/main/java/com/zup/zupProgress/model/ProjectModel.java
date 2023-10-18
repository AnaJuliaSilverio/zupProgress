package com.zup.zupProgress.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "projects")
@Entity(name = "Projects")
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
    @NotBlank
    private String description;
    @NotBlank
    private String trainingInstitution;
    @NotBlank
    private String typeOfDisability;
    //private Instructor instructor;
    //private Leadership leadership;


}
