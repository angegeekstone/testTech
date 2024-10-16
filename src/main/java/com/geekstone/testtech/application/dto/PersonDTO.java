package com.geekstone.testtech.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    @NotBlank(message = "Le numéro d'identité est requis.")
    private String identityNumber;

    @NotBlank(message = "Le prénom est requis.")
    private String firstName;

    @NotBlank(message = "Le nom est requis.")
    private String lastName;

    @NotNull(message = "La date de naissance est requise.")
    private LocalDate dateOfBirth;// Date de naissance
}
