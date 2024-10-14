package com.geekstone.testtech.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PersonResponse {
    private Long id;
    private String identityNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
