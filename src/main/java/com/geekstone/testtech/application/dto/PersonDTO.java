package com.geekstone.testtech.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private String identityNumber;  // Numéro d'identité unique
    private String firstName;       // Prénom
    private String lastName;        // Nom
    private LocalDate dateOfBirth;  // Date de naissance
}
