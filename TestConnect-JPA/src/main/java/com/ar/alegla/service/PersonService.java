package com.ar.alegla.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.alegla.models.Person;
import com.ar.alegla.repository.Repository;

@Service
public class PersonService {

	@Autowired
	private Repository repo;
	
	
	public List<Person> getAllPerson(){
		return repo.findAll();
	}
	
	public String savePerson(Person p) throws Exception {
		try {
			if(p == null) {
				throw new Exception("The data of the person must not be empty");
			}
			repo.save(p);
		}catch(Exception ex) {
			throw new Exception("Error to save the person in db");
		}
		return "The person was saved suceessfully";
	}
	
	
	public List<Person> getPersonByAgeRange(Integer minAge, Integer maxAge) throws Exception{
		if(minAge == null || maxAge == null) {
			throw new Exception ("The fields minAge and maxAge must not be empty"); 
		}		
		return repo.getPersonsByAgeRange(minAge, maxAge);
	}
}
