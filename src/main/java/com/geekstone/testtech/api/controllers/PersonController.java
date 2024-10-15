package com.geekstone.testtech.api.controllers;

import com.geekstone.testtech.application.dto.CreatePersonRequest;
import com.geekstone.testtech.application.dto.PersonResponse;
import com.geekstone.testtech.application.services.commands.PersonCommandService;
import com.geekstone.testtech.application.services.queries.PersonQueryService;
import com.geekstone.testtech.domain.entities.Person;
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
    PersonQueryService personQueryService;

    @PostMapping
    public ResponseEntity<PersonResponse> createPerson(@RequestBody CreatePersonRequest request) {
        PersonResponse response = personCommandService.createPerson(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personQueryService.getAllPersons();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/verify")
    public ResponseEntity<Double> verifyPersonExistence(
            @RequestParam String identityNumber,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String dateOfBirth) {

        // Appeler le service pour vérifier l'existence avec conversion de date intégrée
        double score = personQueryService.verifyPersonExistence(identityNumber, firstName, lastName, dateOfBirth);

        return ResponseEntity.ok(score);
    }
}
