package com.zup.zupProgress.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class StudentDTO {

    private Long idStudent;

    @NotBlank
    private String name;
    @NotBlank
    private Integer age;
    @NotBlank
    private String city;
    @NotBlank
    private String email;
    @NotBlank
    private String image;
    @NotBlank
    private String bio;
    @NotNull
    private Boolean pcd;
    @NotBlank
    private String typeOfDisability;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate contract_end;

}
