package com.LucasVicentee.repository;

import com.LucasVicentee.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
