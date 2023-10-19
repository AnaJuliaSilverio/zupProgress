package com.zup.zupProgress.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "challenge")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(name = "structured_reasoning",nullable = false)
    private String structuredReasoning;
    @Column(name = "basic_codestructures",nullable = false)
    private String basicCodestructures;
    @Column(name = "code_versioning",nullable = false)
    private String codeVersioning;
    @Column(name = "object_orientation",nullable = false)
    private String objectOrientation;
    @Column(name = "tests",nullable = false)
    private String tests;
    @Column(name = "code_cleaning",nullable = false)
    private String codeCleaning;
    @Column(name = "zupper_helps_zupper",nullable = false)
    private String zupperHelpsZupper;
    @Column(name = "communication",nullable = false)
    private String communication;
    @Column(name = "analytical_thinking",nullable = false)
    private String analyticalThinking;
    @Column(name = "growth_mindset",nullable = false)
    private String growthMindset;
    @Column(name = "emotional_intelligence",nullable = false)
    private String emotionalIntelligence;
    @Column(name = "delivery_and_timeManagement",nullable = false)
    private String deliveryAndTimeManagement;
    @Column(name = "self_education",nullable = false)
    private String selfEducation;
    @Enumerated(EnumType.STRING)
    private Assessment assessment;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_assessment")
    private TypeOfAssessment typeOfAssessment;
}
