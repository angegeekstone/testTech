package com.geekstone.testtech.application.services.commands;

import com.geekstone.testtech.application.dto.CreatePersonRequest;
import com.geekstone.testtech.application.dto.PersonResponse;

public interface PersonCommandService {
    public PersonResponse createPerson(CreatePersonRequest request);
}
