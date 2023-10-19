package com.zup.zupProgress.dto;

import com.zup.zupProgress.model.TypeOfAssessment;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeOfAssessment type;
    @NotBlank
    private String description;
    @NotBlank
    private String atributes;
    @NotBlank
    private String status;
}
