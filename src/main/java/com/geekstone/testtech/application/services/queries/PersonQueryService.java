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
public class PersonQueryService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonDomainService personDomainService;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public double verifyPersonExistence(String identityNumber, String firstName, String lastName, String dateOfBirth) {
        // Vérifier si la date de naissance est fournie
        if (dateOfBirth == null || dateOfBirth.trim().isEmpty()) {
            throw new IllegalArgumentException("La date de naissance est requise.");
        }

        // Convertir la date de naissance en LocalDate et gérer les erreurs de format
        LocalDate parsedDateOfBirth;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            parsedDateOfBirth = LocalDate.parse(dateOfBirth, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date de naissance non valide. Le format attendu est 'yyyy-MM-dd'.");
        }

        // Vérifier les champs requis (prénom et nom)
        if (firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Les champs prénom, nom et date de naissance sont requis.");
        }

        // Rechercher la personne dans la base de données
        Optional<Person> personOptional = personRepository.findByIdentityNumber(identityNumber);
        if (personOptional.isEmpty()) {
            throw new IllegalArgumentException("Personne non trouvée avec ce numéro d'identité.");
        }

        Person person = personOptional.get();

        // Créer un DTO avec les informations de la personne à comparer
        PersonDTO personToCompare = new PersonDTO(identityNumber, firstName, lastName, parsedDateOfBirth);

        // Calcul du score de matching
        return personDomainService.calculateMatchingScore(person, personToCompare);
    }

}
