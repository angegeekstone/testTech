package com.geekstone.testtech.application.services.commands;

import com.geekstone.testtech.application.dto.CreatePersonRequest;
import com.geekstone.testtech.application.dto.PersonResponse;
import com.geekstone.testtech.application.mappers.PersonMapper;
import com.geekstone.testtech.domain.entities.Person;
import com.geekstone.testtech.domain.exceptions.PersonCreationException;
import com.geekstone.testtech.domain.repositories.PersonRepository;
import com.geekstone.testtech.domain.services.PersonDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonCommandServiceImpl implements PersonCommandService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonDomainService personDomainService;

    @Autowired
    private PersonMapper personMapper;

    @Override
    @Transactional
    public PersonResponse createPerson(CreatePersonRequest request) {

        // Valider le numéro d'identité avant de créer la personne
        personDomainService.validateIdentityNumberFormat(request.getIdentityNumber());

        try {
            // Validation au niveau du domaine
            if (personDomainService.isPersonExist(request.getIdentityNumber())) {
                throw new PersonCreationException("Une personne avec ce numéro d'identité existe déjà.");
            }

            // Mapping du DTO vers l'entité Person
            Person person = personMapper.toPerson(request);

            // Sauvegarde de la personne dans le repository
            Person savedPerson = personRepository.save(person);

            // Retourner la réponse avec succès
            return personMapper.toPersonResponse(savedPerson);
        } catch (Exception e) {
            throw new PersonCreationException("Erreur lors de la création de la personne: " + e.getMessage());
        }
    }
}
