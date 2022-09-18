package com.sylinx.springbootcache.service;

import com.sylinx.springbootcache.entity.Person;
import com.sylinx.springbootcache.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personsRepository;


    public Optional<Person> findPersonById(Integer id){
        return this.personsRepository.findPersonById(id);
    }


    @Transactional
    public Optional<Person> findPersonByIdWithTransaction(Integer id) throws Exception {
        Optional<Person> person = this.personsRepository.findPersonByIdWithTransaction(id);
        return person;
    }

    public Optional<Person> findPersonByIdWithoutTransaction(Integer id){
        Optional<Person> person = this.personsRepository.findPersonByIdWithoutTransaction(id);
        if(person.isPresent()){
            if(person.get().getId() == 3) {
                throw new RuntimeException("3");
            }
        }
        return person;
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
