package com.geekstone.testtech.application.services.queries;

import com.geekstone.testtech.application.dto.PersonDTO;
import com.geekstone.testtech.domain.entities.Person;

import java.util.List;

public interface PersonQueryService {

    public List<Person> getAllPersons();

    public double verifyPersonExistence(PersonDTO personDTO);
}
