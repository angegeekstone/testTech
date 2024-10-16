package com.geekstone.testtech.application.mappers;

import com.geekstone.testtech.application.dto.CreatePersonRequest;
import com.geekstone.testtech.application.dto.PersonResponse;
import com.geekstone.testtech.domain.entities.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person toPerson(CreatePersonRequest request) {
        Person person = new Person();
        person.setIdentityNumber(request.getIdentityNumber());
        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setDateOfBirth(request.getDateOfBirth());
        return person;
    }

    public PersonResponse toPersonResponse(Person person) {
        return new PersonResponse(person.getId(), person.getIdentityNumber(), person.getFirstName(), person.getLastName(), person.getDateOfBirth());
    }
}
