package com.geekstone.testtech.application.services.queries;


import com.geekstone.testtech.application.dto.PersonDTO;
import com.geekstone.testtech.domain.entities.Person;
import com.geekstone.testtech.domain.repositories.PersonRepository;
import com.geekstone.testtech.domain.services.PersonDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonQueryServiceImpl implements PersonQueryService{
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonDomainService personDomainService;

    @Override
    public List<Person> getAllPersons() {

        return personRepository.findAll();
    }

    @Override
    public double verifyPersonExistence(PersonDTO personDTO) {
        // Vérifier si la date de naissance est fournie
        if (personDTO.getDateOfBirth() == null) {
            throw new IllegalArgumentException("La date de naissance est requise.");
        }

        // Vérifier les champs requis (prénom, nom et numéro d'identité)
        if (personDTO.getFirstName() == null || personDTO.getFirstName().trim().isEmpty() ||
                personDTO.getLastName() == null || personDTO.getLastName().trim().isEmpty() ||
                personDTO.getIdentityNumber() == null || personDTO.getIdentityNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Les champs prénom, nom et numéro d'identité sont requis.");
        }

        // Rechercher la personne dans la base de données par numéro d'identité
        Optional<Person> personOptional = personRepository.findByIdentityNumber(personDTO.getIdentityNumber());
        if (personOptional.isEmpty()) {
            throw new IllegalArgumentException("Personne non trouvée avec ce numéro d'identité.");
        }

        Person person = personOptional.get();

        // Calcul du score de matching
        return personDomainService.calculateMatchingScore(person, personDTO);
    }


}
