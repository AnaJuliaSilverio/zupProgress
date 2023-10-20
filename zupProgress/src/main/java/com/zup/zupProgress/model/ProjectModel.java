package com.zup.zupProgress.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @NotBlank
    private String description;
    @NotBlank
    private String trainingInstitution;
    //private Instructor instructor;
    //private Leadership leadership;


}
