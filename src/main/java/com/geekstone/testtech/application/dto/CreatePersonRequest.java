package com.geekstone.testtech.application.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePersonRequest {
    private String identityNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}

