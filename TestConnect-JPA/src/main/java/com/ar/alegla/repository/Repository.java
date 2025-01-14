package com.ar.alegla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ar.alegla.models.Person;

public interface Repository extends JpaRepository<Person, Integer>{
	
	@Query("SELECT p FROM Person p WHERE p.age >= :minAge AND p.age <= :maxAge")
    List<Person> getPersonsByAgeRange(@Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge);

	

}
