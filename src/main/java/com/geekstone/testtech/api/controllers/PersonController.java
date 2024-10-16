package com.geekstone.testtech.api.controllers;

import com.geekstone.testtech.api.models.ApiResponse;
import com.geekstone.testtech.application.dto.CreatePersonRequest;
import com.geekstone.testtech.application.dto.PersonDTO;
import com.geekstone.testtech.application.dto.PersonResponse;
import com.geekstone.testtech.application.services.commands.PersonCommandService;
import com.geekstone.testtech.application.services.queries.PersonQueryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    @Autowired
    private PersonCommandService personCommandService;

    @Autowired
    private PersonQueryService personQueryService;

    @PostMapping
    public ResponseEntity<ApiResponse<PersonResponse>> createPerson(@Valid @RequestBody CreatePersonRequest request) {
        PersonResponse response = personCommandService.createPerson(request);
        return ResponseEntity.ok(new ApiResponse<>(200, "Personne créée avec succès", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PersonResponse>>> getAllPersons() {
        List<PersonResponse> persons = personQueryService.getAllPersons().stream()
                .map(person -> new PersonResponse(person.getId(), person.getIdentityNumber(), person.getFirstName(), person.getLastName(), person.getDateOfBirth()))
                .toList();

        return ResponseEntity.ok(new ApiResponse<>(200, "Liste des personnes récupérée avec succès", persons));
    }

    @GetMapping("/verify")
    public ResponseEntity<ApiResponse<Double>> verifyPersonExistence(@Valid @RequestParam PersonDTO personDTO) {
        double score = personQueryService.verifyPersonExistence(personDTO);
        return ResponseEntity.ok(new ApiResponse<>(200, "Vérification effectuée avec succès", score));
    }
}
