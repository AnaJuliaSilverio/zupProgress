package com.zup.zupProgress.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private Long idStudent;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String image;

    @OneToOne
    @JoinColumn(nullable = false, name = "fk_mentor")
    private MentorModel fkMentor;

    @Column(nullable = false)
    private String bio;

    @Column(nullable = false)
    private String pcd;

    @Column(nullable = false)
    private String typeOfDisability;

    @OneToOne
    @JoinColumn(nullable = false, name = "fk_project")
    private ProjectModel fkProject;

    @Column(nullable = false)
    private LocalDate contract_end;


}
