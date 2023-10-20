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
    @NotNull
    private Integer age;
    @NotBlank
    private String bio;
    @NotBlank
    private String city;
    @NotNull
    private LocalDate contract_end;
    @NotBlank
    private String email;
    private String project;
    private String mentor;
    @NotBlank
    private String name;
    @NotBlank
    private String pcd;
    private String typeOfDisability;
    @NotBlank
    private String image;




}
