package com.zup.zupProgress.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackAtributesDTO {
    private Long id;
    @NotBlank
    private String atributes;
}
