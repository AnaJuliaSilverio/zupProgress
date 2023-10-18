package com.zup.zupProgress.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorDTO {
    private Long idMentor;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String bio;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startMentoring;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endMentoring;
    @NotNull
    private Boolean training;

    public MentorDTO(String name, String email, String bio, LocalDate startMentoring, LocalDate endMentoring, Boolean training) {
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.startMentoring = startMentoring;
        this.endMentoring = endMentoring;
        this.training = training;
    }
}
