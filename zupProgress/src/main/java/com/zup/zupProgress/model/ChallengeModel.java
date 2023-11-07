package com.zup.zupProgress.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "challenge")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_challenge")
    private Long id;
    @Column(nullable = false)
    private String title;
}
