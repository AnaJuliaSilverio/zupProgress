package com.zup.zupProgress.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "mentor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mentor")
    private Long idMentor;
    private String name;
    private String email;
    private String bio;
    private LocalDate startMentoring;
    private LocalDate endMentoring;
    private Boolean training;


}
