package com.geekstone.testtech.application.services.commands;


import com.geekstone.testtech.application.dto.CreatePersonRequest;
import com.geekstone.testtech.application.dto.PersonResponse;
import com.geekstone.testtech.domain.entities.Person;
import com.geekstone.testtech.domain.repositories.PersonRepository;
import com.geekstone.testtech.domain.services.PersonDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonCommandService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonDomainService personDomainService;

    public PersonResponse createPerson(CreatePersonRequest request) {
        Person person = new Person();
        person.setIdentityNumber(request.getIdentityNumber());
        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setDateOfBirth(request.getDateOfBirth());

        personRepository.save(person);

        return new PersonResponse(person.getId(), person.getIdentityNumber(), person.getFirstName(), person.getLastName(), person.getDateOfBirth());
    }

}
