package com.zup.zupProgress.dto;

import com.zup.zupProgress.model.Assessment;
import com.zup.zupProgress.model.TypeOfAssessment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeDTO {
    private Long id;
    @NotBlank
    private String title;
}
