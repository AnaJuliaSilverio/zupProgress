package com.zup.zupProgress.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instrutor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstrutorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_instrutor;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;
}
