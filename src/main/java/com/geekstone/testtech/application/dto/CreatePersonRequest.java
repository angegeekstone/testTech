package com.geekstone.testtech.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePersonRequest {
    @NotBlank(message = "Le numéro d'identité est requis.")
    private String identityNumber;

    @NotBlank(message = "Le prénom est requis.")
    private String firstName;

    @NotBlank(message = "Le nom est requis.")
    private String lastName;

    @NotNull(message = "La date de naissance est requise.")
    private LocalDate dateOfBirth;
}

