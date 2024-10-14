package com.geekstone.testtech.domain.services;

import com.geekstone.testtech.application.dto.PersonDTO;
import com.geekstone.testtech.domain.entities.Person;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class PersonDomainService {

    // Formatter pour convertir LocalDate en String
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public double calculateMatchingScore(Person person, PersonDTO personToCompare) {
        double firstNameScore = 0;
        double lastNameScore = 0;
        double dateOfBirthScore = 0;

        // Comparaison des prénoms
        if (person.getFirstName() != null && personToCompare.getFirstName() != null) {
            firstNameScore = StringUtils.getJaroWinklerDistance(
                    person.getFirstName(), personToCompare.getFirstName()) * 100;
        }

        // Comparaison des noms
        if (person.getLastName() != null && personToCompare.getLastName() != null) {
            lastNameScore = StringUtils.getJaroWinklerDistance(
                    person.getLastName(), personToCompare.getLastName()) * 100;
        }

        // Comparaison des dates de naissance (conversion de LocalDate en String)
        if (person.getDateOfBirth() != null && personToCompare.getDateOfBirth() != null) {
            String personDateOfBirth = person.getDateOfBirth().format(formatter); // Conversion en String
            String personToCompareDateOfBirth = personToCompare.getDateOfBirth().format(formatter); // Conversion en String
            dateOfBirthScore = StringUtils.getJaroWinklerDistance(
                    personDateOfBirth, personToCompareDateOfBirth) * 100;
        }

        // Calcul du score moyen
        return (firstNameScore + lastNameScore + dateOfBirthScore) / 3;
    }
}
