package com.zup.zupProgress.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leadership")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeadershipModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_leadership")
    private Long idLeadership;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

}
