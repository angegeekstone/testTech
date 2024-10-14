package com.geekstone.testtech.domain.repositories;


import com.geekstone.testtech.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByIdentityNumber(String identityNumber);
}

