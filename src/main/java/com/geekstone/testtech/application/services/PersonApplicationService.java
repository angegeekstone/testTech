package com.geekstone.testtech.application.services;

import com.geekstone.testtech.application.dto.CreatePersonRequest;
import com.geekstone.testtech.application.dto.PersonDTO;
import com.geekstone.testtech.application.dto.PersonResponse;
import com.geekstone.testtech.domain.entities.Person;
import com.geekstone.testtech.domain.repositories.PersonRepository;
import com.geekstone.testtech.domain.services.PersonDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PersonApplicationService {

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

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public double verifyPersonExistence(String identityNumber, String firstName, String lastName, String dateOfBirth) {
        // Convertir la date de naissance en LocalDate
        LocalDate parsedDateOfBirth;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            parsedDateOfBirth = LocalDate.parse(dateOfBirth, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Date de naissance non valide.");
        }

        // Vérifier les champs requis
        if (firstName == null || lastName == null || parsedDateOfBirth == null) {
            throw new IllegalArgumentException("Les champs prénom, nom et date de naissance sont requis.");
        }

        // Rechercher la personne dans la base de données
        Optional<Person> personOptional = personRepository.findByIdentityNumber(identityNumber);
        if (!personOptional.isPresent()) {
            throw new IllegalArgumentException("Personne non trouvée avec ce numéro d'identité.");
        }

        Person person = personOptional.get();

        // Créer un DTO avec les informations de la personne à comparer
        PersonDTO personToCompare = new PersonDTO(identityNumber, firstName, lastName, parsedDateOfBirth);

        // Calcul du score de matching
        return personDomainService.calculateMatchingScore(person, personToCompare);
    }
}
