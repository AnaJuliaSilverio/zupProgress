package com.zup.zupProgress.dto;

import com.zup.zupProgress.model.Assessment;
import com.zup.zupProgress.model.TypeOfAssessment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChallengeDTO {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String structuredReasoning;
    @NotBlank
    private String basicCodestructures;
    @NotBlank
    private String codeVersioning;
    @NotBlank
    private String objectOrientation;
    @NotBlank
    private String tests;
    @NotBlank
    private String codeCleaning;
    @NotBlank
    private String zupperHelpsZupper;
    @NotBlank
    private String communication;
    @NotBlank
    private String analyticalThinking;
    @NotBlank
    private String growthMindset;
    @NotBlank
    private String emotionalIntelligence;
    @NotBlank
    private String deliveryAndTimeManagement;
    @NotBlank
    private String selfEducation;
    @NotNull
    private Assessment assessment;
    @NotNull
    private TypeOfAssessment typeOfAssessment;
}
