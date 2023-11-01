package com.zup.zupProgress.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeadershipDTO {
    private Long idLeadership;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
}
