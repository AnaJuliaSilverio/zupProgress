package com.zup.zupProgress.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="feedback")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_feedback")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeOfAssessment type;
    private String description;
    private String atributes;
    private String status;
    @OneToOne
    @JoinColumn(name = "fk_challenge")
    private ChallengeModel challengeModel;
    @OneToOne
    @JoinColumn(name = "fk_student")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StudentModel studentModel;
}
