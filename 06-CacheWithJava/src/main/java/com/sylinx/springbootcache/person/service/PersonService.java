package com.sylinx.springbootcache.person.service;

import com.sylinx.springbootcache.person.entity.Person;
import com.sylinx.springbootcache.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personsRepository;

    public Optional<Person> findPersonById(Integer id){
        return this.personsRepository.findPersonById(id);
    }

    public Person updatePerson(Person person){
        return this.personsRepository.updatePerson(person);
    }

    public Optional<Person> deletePerson(Integer id){
        return this.personsRepository.deletePerson(id);
    }

    public boolean insertPerson(Person person){
        return this.personsRepository.insertPerson(person);
    }
}
