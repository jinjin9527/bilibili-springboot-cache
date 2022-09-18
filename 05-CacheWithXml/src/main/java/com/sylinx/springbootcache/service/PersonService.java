package com.sylinx.springbootcache.service;

import com.sylinx.springbootcache.entity.Person;
import com.sylinx.springbootcache.repository.PersonRepository;
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

    public Optional<Person> find1PersonById(Integer id){
        Optional<Person> personById = this.personsRepository.findPersonById(id);
        if(personById.isPresent()){
            personById.get().setName("find1_" + personById.get().getName());
        }
        return this.personsRepository.findPersonById(id);
    }

    public Optional<Person> find2PersonById(Integer id){
        Optional<Person> personById = this.personsRepository.findPersonById(id);
        if(personById.isPresent()){
            personById.get().setName("find2_" + personById.get().getName());
        }
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
